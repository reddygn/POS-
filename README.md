# POS
Software Solution for Retail - POS

URL to access H2 Console - http://localhost:8080/h2-console

Request to add products into the H2 In memory DB :

POST http://localhost:8080/api/order/create
```json

[
    {
        "productName": "Gaming Chair",
        "price": 133.21,
        "customerEmail": "nav@gmail.com"
    },
    {
        "productName": "Computer Table",
        "price": 190,
        "customerEmail": "nav@gmail.com"
    },
    {
        "productName": "Phone Mount",
        "price": 23.33,
        "customerEmail": "nav@gmail.com"
    },
    {
        "productName": "Keyboard",
        "price": 21.67,
        "customerEmail": "nav@gmail.com"
    }
]
```



Request to print a receipt for a given customer with transactionId

GET :: http://localhost:8080/api/receipt/print/{transactionId}
