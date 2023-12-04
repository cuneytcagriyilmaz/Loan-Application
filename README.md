# Loan-Application
Full stack credit validation project using spring boot and thymeleaf.



### Features
- New users can be defined to the system, existing customers can be updated or deleted.- Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
- If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the user's loan application is approved and a limit of 10,000 TL is assigned to the user. (Credit Result: Approval). If collateral is provided, 10 percent of the collateral amount is added to the credit limit.
- If the credit score is between 500 points and 1000 points and the monthly income is between 5000 TL and 10,000 TL, the user's loan application is approved and a limit of 20,000 TL is assigned to the user. (Credit Result: Approval) If collateral is provided, 20 percent of the collateral amount is added to the credit limit.
- If the credit score is between 500 points and 1000 points and the monthly income is over 10,000 TL, the user's loan application is approved and a limit of MONTHLY INCOME INFORMATION * CREDIT LIMIT MULTIPLIER/2 is assigned to the user. (Credit Result: Approval) If collateral is provided, 25 percent of the collateral amount is added to the credit limit.
- If the credit score is equal to or above 1000 points, a limit equal to the MONTHLY INCOME INFORMATION * CREDIT LIMIT MULTIPLIER is assigned to the user. (Credit Result: Approval) If collateral is provided, 50 percent of the collateral amount is added to the credit limit.
- As a result of the loan being finalized, the relevant application is recorded in the database. Afterwards, an informative SMS is sent to the relevant phone number and approval status information (rejection or approval) and limit information are returned from the endpoint.
- A completed loan application can only be inquired with the identification number and date of birth information. If the date of birth and identity information do not match, it should not be questioned.
- Notes: - Credit limit multiplier is 4 by default.
- Notes: - The frontend was made using thymeleaf.

## Diagram
![diagram](https://user-images.githubusercontent.com/101995377/221354056-06cee3cb-4472-4679-a507-3284a73d5210.png)


## REST API
![Register](https://user-images.githubusercontent.com/101995377/221353969-d3290d48-7fad-41cb-ade4-7d1f9245317e.png)

![Login](https://user-images.githubusercontent.com/101995377/221353975-55ff7b19-e5c6-4b69-b49e-a21e1780fb39.png)

![Update](https://user-images.githubusercontent.com/101995377/221353979-25902cf5-0ddb-486f-b3be-a60b8bde3298.png)


![MakeAnApplication](https://user-images.githubusercontent.com/101995377/221353987-4773c61e-8ef2-4f52-a1e6-b032d735931b.png)

![AddCredit](https://user-images.githubusercontent.com/101995377/221353982-12b5428c-97e9-44c3-83a9-8bc9d2170bdb.png)

![Delete](https://user-images.githubusercontent.com/101995377/221353980-ae2b3b57-b2de-4447-aa93-b223197a0abe.png)




