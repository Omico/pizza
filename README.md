# Programming Assignment 1 (100 points)

Design a pizza ordering mobile application. This application will need to
allow the user to type their information, customize their pizza, inform
the user what the total price of the order is. After the user submit the
order, the order details will be displayed. Assume each customer can
only order one pizza.

## The user interface (UI) (60 points)

### MainPage

Source code: [MainPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/MainPage.kt)

- Toppings must include but not limited to the following options
  (such as checkboxes or Spinner): Pepperoni, Chicken, Mushroom,
  Green Pepper, Olive, Extra cheese (10 points)
- Size options include Small / Medium / Large (such as Radio
  Buttons, or Spinner) (10 points)
- Include pizza pictures in the design(5 points)
- Next Button (10 points)
  After clicking the next button, the customer page will be
  launched. The pizza choices will be passed to the customer page.

### CustomerPage

Source code: [CustomerPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/CustomerPage.kt)

- Customer Information: (10 points)
    - Customer name
    - Phone number
    - Email
    - Address
- Receipt Information area (such as TextView)
- Submit Button (5 points)
    - After clicking the submit button, the order details including
      pizza toppings, pizza size, pizza price and customer
      information will be displayed in the receipt information area

### HelpPage

Source code: [HelpPage.kt](pizza/app/src/main/kotlin/me/omico/pizza/HelpPage.kt)

- Display the contact information (2 points)
- Display the store location information (2 points)
- Allow users to navigate to this page from either the MainPage or
  the CustomerPage (6 points)

## Model (20 points)

Source code: [PizzaViewModel.kt](pizza/app/src/main/kotlin/me/omico/pizza/PizzaViewModel.kt)

Create a separate class to store order details including:
customer name, phone number, email, address, pizza toppings, pizza
size and order price.
The separate class should include one method to calculate the order
price.

## Handle Rotations and restore activity state (20 points)

- Save and restore activity state(UI states) after the user rotates the
  device.
- Provide a different layout file for landscape orientation.
