# Programming Assignment 2 (100 points)

Design a pizza ordering mobile application. This application will need to
allow the user to type their information, customize their pizza, inform
the user what the total price of the order is. After the user submit the
order, the order details will be displayed. Assume each customer can
only order one pizza.

## The user interface (UI) (50 points)

The application should contain one activity and three fragments. Each
fragment contains one page of the following three pages:

### MainPage

Source code: [MainPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/MainPage.kt)

- Toppings must include but not limited to the following options
  (such as checkboxes or Spinner): Pepperoni, Chicken, Mushroom,
  Green Pepper, Olive, Extra cheese (10 points)
- Size options include Small / Medium / Large (such as Radio
  Buttons, or Spinner) (10 points)
- Include pizza pictures in the design(5 points)
- Next Button and on-click event (5 points)
  After clicking the next button, the customer page will be
  launched. The pizza choices will be passed to the customer page.

### CustomerPage

Source code: [CustomerPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/CustomerPage.kt)

- Customer Information: (5 points)
    - Customer name
    - Phone number
    - Email
    - Address
- Submit Button and on-click event (5 points)
    - After clicking the submit button, the receipt page will be
      launched. The order details including pizza toppings, pizza
      size, pizza price and customer information will be displayed
      in the receipt information area on the Receipt page.
    - After clicking the submit button, save the receipt as a txt file
      in the internal storage.

### ReceiptPage

Source code: [ReceiptPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/ReceiptPage.kt)

- Receipt Information area (such as TextView) (5 points)
- Display the contact information (5 points)

## Model (15 points)

Source code: [PizzaViewModel.kt](pizza/app/src/main/kotlin/me/omico/pizza/PizzaViewModel.kt)

Create a separate class to store order details including:
customer name, phone number, email, address, pizza toppings, pizza
size and order price.
The separate class should include one method to calculate the order
price.
The separate class should include one method to create the receipt
string.

## Navigation and Passing data between different fragments(screens) (25 points)

- You may use FragmentManager or Navigation Graph to implement the
  navigation between an app’s screens.
- You may use Bundle object or SharedPreference object to pass data
  between different fragments.

## Save the receipt as a txt file (“receipt.txt”) in the internal storage. (10 points)

## Bonus (5 points): Navigation Graph
