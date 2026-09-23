const API_BASE_URL = "http://localhost:8080/api";


// ======================================================
// DASHBOARD
// ======================================================

async function loadDashboard() {

    try {

        const [silos, telemetry, customers, vendors] = await Promise.all([
            fetch(`${API_BASE_URL}/silos/units`).then(response => response.json()),
            fetch(`${API_BASE_URL}/silos/telemetry`).then(response => response.json()),
            fetch(`${API_BASE_URL}/customers`).then(response => response.json()),
            fetch(`${API_BASE_URL}/vendors`).then(response => response.json())
        ]);

        document.getElementById("siloCount").textContent = silos.length;

        document.getElementById("customerCount").textContent =
            customers.length;

        document.getElementById("vendorCount").textContent =
            vendors.length;

        const activeFans = telemetry.filter(
            item => item.fanActivated === true
        ).length;

        document.getElementById("fanCount").textContent =
            activeFans;

        loadSiloTable(silos);

    } catch (error) {

        console.error("Dashboard error:", error);

    }
}


// ======================================================
// DASHBOARD SILO TABLE
// ======================================================

function loadSiloTable(silos) {

    const tableBody =
        document.getElementById("siloTableBody");

    tableBody.innerHTML = "";

    silos.forEach(silo => {

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${silo.id}</td>
            <td>${silo.siloName}</td>
            <td>${silo.location}</td>
            <td>${silo.capacity}</td>
            <td>${silo.grainType}</td>
            <td class="status-active">${silo.status}</td>
        `;

        tableBody.appendChild(row);

    });
}


// ======================================================
// SIDEBAR NAVIGATION
// ======================================================

function showSection(sectionId) {

    const sections = [
        "dashboardSection",
        "siloSection",
        "telemetrySection",
        "customerSection",
        "vendorSection",
        "salesSection",
        "purchaseSection",
        "paymentSection",
        "reportSection",
        "budgetSection"
    ];

    sections.forEach(id => {

        const section =
            document.getElementById(id);

        if (section) {
            section.style.display = "none";
        }

    });


    const selectedSection =
        document.getElementById(sectionId);

    if (selectedSection) {
        selectedSection.style.display = "block";
    }


    // Load module data

    if (sectionId === "dashboardSection") {
        loadDashboard();
    }

    if (sectionId === "siloSection") {
        loadSiloManagement();
    }

    if (sectionId === "telemetrySection") {
        loadTelemetry();
    }

    if (sectionId === "customerSection") {
        loadCustomers();
    }

    if (sectionId === "vendorSection") {
        loadVendors();
    }

    if (sectionId === "salesSection") {
        loadSalesOrders();
        loadCustomerInvoices();
    }

    if (sectionId === "purchaseSection") {
        loadPurchaseOrders();
        loadVendorBills();
    }

    if (sectionId === "paymentSection") {
        loadBankPayments();
        loadVendorPayments();
    }

    if (sectionId === "reportSection") {
        loadReports();
    }

    if (sectionId === "budgetSection") {
        loadBudgets();
    }
}


// ======================================================
// SILO MANAGEMENT
// ======================================================

async function loadSiloManagement() {

    try {

        const response =
            await fetch(`${API_BASE_URL}/silos/units`);

        const silos =
            await response.json();

        const tableBody =
            document.getElementById("siloManagementTable");

        tableBody.innerHTML = "";

        silos.forEach(silo => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${silo.id}</td>
                <td>${silo.siloName}</td>
                <td>${silo.location}</td>
                <td>${silo.capacity}</td>
                <td>${silo.grainType}</td>
                <td>${silo.status}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading silos:",
            error
        );

    }
}


// ======================================================
// ADD SILO
// ======================================================

async function addSilo() {

    const siloName =
        document.getElementById("siloName").value;

    const location =
        document.getElementById("siloLocation").value;

    const capacity =
        document.getElementById("siloCapacity").value;

    const grainType =
        document.getElementById("grainType").value;

    const status =
        document.getElementById("siloStatus").value;


    if (!siloName ||
        !location ||
        !capacity ||
        !grainType) {

        alert("Please fill all silo details.");

        return;
    }


    const siloData = {

        siloName: siloName,

        location: location,

        capacity: Number(capacity),

        grainType: grainType,

        status: status

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/silos/units`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(siloData)
                }
            );


        if (!response.ok) {

            throw new Error(
                "Failed to add silo."
            );

        }


        alert(
            "Silo added successfully!"
        );


        document.getElementById("siloName").value = "";
        document.getElementById("siloLocation").value = "";
        document.getElementById("siloCapacity").value = "";
        document.getElementById("grainType").value = "";


        loadSiloManagement();

        loadDashboard();

    } catch (error) {

        console.error(
            "Add silo error:",
            error
        );

        alert(
            "Unable to add silo."
        );

    }
}


// ======================================================
// TELEMETRY
// ======================================================

async function loadTelemetry() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/silos/telemetry`
            );

        const telemetry =
            await response.json();

        const tableBody =
            document.getElementById(
                "telemetryTableBody"
            );

        tableBody.innerHTML = "";


        telemetry.forEach(item => {

            const row =
                document.createElement("tr");

            const fanStatus =
                item.fanActivated
                    ? "ON"
                    : "OFF";


            row.innerHTML = `
                <td>${item.id}</td>
                <td>${item.siloId}</td>
                <td>${item.moisture}%</td>
                <td>${item.temperature} °C</td>
                <td>${fanStatus}</td>
                <td>${item.recordedAt}</td>
            `;


            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading telemetry:",
            error
        );

    }
}


// ======================================================
// RECORD TELEMETRY
// ======================================================

async function recordTelemetry() {

    const siloId =
        document.getElementById(
            "telemetrySiloId"
        ).value;

    const moisture =
        document.getElementById(
            "moisture"
        ).value;

    const temperature =
        document.getElementById(
            "temperature"
        ).value;


    if (!siloId ||
        !moisture ||
        !temperature) {

        alert(
            "Please enter all telemetry details."
        );

        return;
    }


    const telemetryData = {

        siloId: Number(siloId),

        moisture: Number(moisture),

        temperature: Number(temperature)

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/silos/telemetry`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(
                        telemetryData
                    )
                }
            );


        if (!response.ok) {

            throw new Error(
                "Failed to record telemetry."
            );

        }


        const result =
            await response.json();


        if (result.fanActivated) {

            alert(
                "Telemetry recorded. Moisture is high, so the fan was ACTIVATED."
            );

        } else {

            alert(
                "Telemetry recorded. Fan remains OFF."
            );

        }


        document.getElementById(
            "telemetrySiloId"
        ).value = "";

        document.getElementById(
            "moisture"
        ).value = "";

        document.getElementById(
            "temperature"
        ).value = "";


        loadTelemetry();

        loadDashboard();


    } catch (error) {

        console.error(
            "Telemetry error:",
            error
        );

        alert(
            "Unable to record telemetry."
        );

    }
}


// ======================================================
// CUSTOMERS
// ======================================================

async function loadCustomers() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/customers`
            );

        const customers =
            await response.json();

        const tableBody =
            document.getElementById(
                "customerTableBody"
            );

        tableBody.innerHTML = "";


        customers.forEach(customer => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${customer.id}</td>
                <td>${customer.cooperativeName}</td>
                <td>${customer.contactPerson}</td>
                <td>${customer.phone}</td>
                <td>${customer.email}</td>
                <td>${customer.address}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading customers:",
            error
        );

    }
}


// ======================================================
// ADD CUSTOMER
// ======================================================

async function addCustomer() {

    const cooperativeName =
        document.getElementById(
            "cooperativeName"
        ).value;

    const contactPerson =
        document.getElementById(
            "customerContact"
        ).value;

    const phone =
        document.getElementById(
            "customerPhone"
        ).value;

    const email =
        document.getElementById(
            "customerEmail"
        ).value;

    const address =
        document.getElementById(
            "customerAddress"
        ).value;


    if (!cooperativeName ||
        !contactPerson ||
        !phone ||
        !email ||
        !address) {

        alert(
            "Please fill all customer details."
        );

        return;
    }


    const customerData = {

        cooperativeName: cooperativeName,

        contactPerson: contactPerson,

        phone: phone,

        email: email,

        address: address

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/customers`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(
                        customerData
                    )
                }
            );


        if (!response.ok) {

            throw new Error(
                "Failed to add customer."
            );

        }


        alert(
            "Customer added successfully!"
        );


        document.getElementById(
            "cooperativeName"
        ).value = "";

        document.getElementById(
            "customerContact"
        ).value = "";

        document.getElementById(
            "customerPhone"
        ).value = "";

        document.getElementById(
            "customerEmail"
        ).value = "";

        document.getElementById(
            "customerAddress"
        ).value = "";


        loadCustomers();

        loadDashboard();


    } catch (error) {

        console.error(
            "Customer error:",
            error
        );

        alert(
            "Unable to add customer."
        );

    }
}


// ======================================================
// VENDORS
// ======================================================

async function loadVendors() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/vendors`
            );

        const vendors =
            await response.json();

        const tableBody =
            document.getElementById(
                "vendorTableBody"
            );

        tableBody.innerHTML = "";


        vendors.forEach(vendor => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${vendor.id}</td>
                <td>${vendor.vendorName}</td>
                <td>${vendor.contactPerson}</td>
                <td>${vendor.phone}</td>
                <td>${vendor.email}</td>
                <td>${vendor.address}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading vendors:",
            error
        );

    }
}


// ======================================================
// ADD VENDOR
// ======================================================

async function addVendor() {

    const vendorName =
        document.getElementById(
            "vendorName"
        ).value;

    const contactPerson =
        document.getElementById(
            "vendorContact"
        ).value;

    const phone =
        document.getElementById(
            "vendorPhone"
        ).value;

    const email =
        document.getElementById(
            "vendorEmail"
        ).value;

    const address =
        document.getElementById(
            "vendorAddress"
        ).value;


    if (!vendorName ||
        !contactPerson ||
        !phone ||
        !email ||
        !address) {

        alert(
            "Please fill all vendor details."
        );

        return;
    }


    const vendorData = {

        vendorName: vendorName,

        contactPerson: contactPerson,

        phone: phone,

        email: email,

        address: address

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/vendors`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(
                        vendorData
                    )
                }
            );


        if (!response.ok) {

            throw new Error(
                "Failed to add vendor."
            );

        }


        alert(
            "Vendor added successfully!"
        );


        document.getElementById(
            "vendorName"
        ).value = "";

        document.getElementById(
            "vendorContact"
        ).value = "";

        document.getElementById(
            "vendorPhone"
        ).value = "";

        document.getElementById(
            "vendorEmail"
        ).value = "";

        document.getElementById(
            "vendorAddress"
        ).value = "";


        loadVendors();

        loadDashboard();


    } catch (error) {

        console.error(
            "Vendor error:",
            error
        );

        alert(
            "Unable to add vendor."
        );

    }
}


// ======================================================
// SALES ORDERS
// ======================================================

async function loadSalesOrders() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/sales-orders`
            );

        const orders =
            await response.json();

        const tableBody =
            document.getElementById(
                "salesOrderTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        orders.forEach(order => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.customerId}</td>
                <td>${order.serviceItemId}</td>
                <td>${order.quantity}</td>
                <td>${order.totalAmount}</td>
                <td>${order.status}</td>
                <td>${order.orderDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading sales orders:",
            error
        );

    }
}


// ======================================================
// CUSTOMER INVOICES
// ======================================================

async function loadCustomerInvoices() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/customer-invoices`
            );

        const invoices =
            await response.json();

        const tableBody =
            document.getElementById(
                "customerInvoiceTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        invoices.forEach(invoice => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${invoice.id}</td>
                <td>${invoice.salesOrderId}</td>
                <td>${invoice.customerId}</td>
                <td>${invoice.invoiceAmount}</td>
                <td>${invoice.status}</td>
                <td>${invoice.invoiceDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading customer invoices:",
            error
        );

    }
}


// ======================================================
// PURCHASE ORDERS
// ======================================================

async function loadPurchaseOrders() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/purchase-orders`
            );

        const orders =
            await response.json();

        const tableBody =
            document.getElementById(
                "purchaseOrderTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        orders.forEach(order => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.vendorId}</td>
                <td>${order.itemName}</td>
                <td>${order.quantity}</td>
                <td>${order.totalAmount}</td>
                <td>${order.status}</td>
                <td>${order.orderDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading purchase orders:",
            error
        );

    }
}


// ======================================================
// VENDOR BILLS
// ======================================================

async function loadVendorBills() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/vendor-bills`
            );

        const bills =
            await response.json();

        const tableBody =
            document.getElementById(
                "vendorBillTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        bills.forEach(bill => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${bill.id}</td>
                <td>${bill.purchaseOrderId}</td>
                <td>${bill.vendorId}</td>
                <td>${bill.billAmount}</td>
                <td>${bill.status}</td>
                <td>${bill.billDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading vendor bills:",
            error
        );

    }
}


// ======================================================
// BANK PAYMENTS
// ======================================================

async function loadBankPayments() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/bank-payments`
            );

        const payments =
            await response.json();

        const tableBody =
            document.getElementById(
                "bankPaymentTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        payments.forEach(payment => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${payment.id}</td>
                <td>${payment.invoiceId}</td>
                <td>${payment.customerId}</td>
                <td>${payment.amount}</td>
                <td>${payment.paymentMethod}</td>
                <td>${payment.status}</td>
                <td>${payment.paymentDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading bank payments:",
            error
        );

    }
}


// ======================================================
// VENDOR PAYMENTS
// ======================================================

async function loadVendorPayments() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/vendor-payments`
            );

        const payments =
            await response.json();

        const tableBody =
            document.getElementById(
                "vendorPaymentTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";

        payments.forEach(payment => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${payment.id}</td>
                <td>${payment.vendorBillId}</td>
                <td>${payment.vendorId}</td>
                <td>${payment.amount}</td>
                <td>${payment.paymentMethod}</td>
                <td>${payment.status}</td>
                <td>${payment.paymentDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading vendor payments:",
            error
        );

    }
}


// ======================================================
// FINANCIAL REPORTS
// ======================================================

async function loadReports() {

    try {

        const [profitLoss, balanceSheet] =
            await Promise.all([

                fetch(
                    `${API_BASE_URL}/reports/profit-loss`
                ).then(response => response.json()),

                fetch(
                    `${API_BASE_URL}/reports/balance-sheet`
                ).then(response => response.json())

            ]);


        const incomeElement =
            document.getElementById(
                "totalIncome"
            );

        const expenseElement =
            document.getElementById(
                "totalExpense"
            );

        const profitElement =
            document.getElementById(
                "netProfit"
            );

        const assetsElement =
            document.getElementById(
                "totalAssets"
            );

        const liabilitiesElement =
            document.getElementById(
                "totalLiabilities"
            );


        if (incomeElement)
            incomeElement.textContent =
                profitLoss.totalIncome;

        if (expenseElement)
            expenseElement.textContent =
                profitLoss.totalExpense;

        if (profitElement)
            profitElement.textContent =
                profitLoss.netProfit;

        if (assetsElement)
            assetsElement.textContent =
                balanceSheet.totalAssets;

        if (liabilitiesElement)
            liabilitiesElement.textContent =
                balanceSheet.totalLiabilities;


    } catch (error) {

        console.error(
            "Error loading reports:",
            error
        );

    }
}


// ======================================================
// BUDGET
// ======================================================

async function loadBudgets() {

    try {

        const response =
            await fetch(
                `${API_BASE_URL}/reports/budget`
            );

        const budgets =
            await response.json();

        const tableBody =
            document.getElementById(
                "budgetTableBody"
            );

        if (!tableBody) return;

        tableBody.innerHTML = "";


        budgets.forEach(budget => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${budget.budgetId}</td>
                <td>${budget.accountId}</td>
                <td>${budget.period}</td>
                <td>${budget.budgetAmount}</td>
                <td>${budget.actualAmount}</td>
                <td>${budget.remainingAmount}</td>
                <td>${budget.description}</td>
            `;

            tableBody.appendChild(row);

        });


    } catch (error) {

        console.error(
            "Error loading budgets:",
            error
        );

    }
}


// ======================================================
// START APPLICATION
// ======================================================

window.onload = function () {

    loadDashboard();

};