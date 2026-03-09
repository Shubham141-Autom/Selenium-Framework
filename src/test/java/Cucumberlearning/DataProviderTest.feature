@tag
Feature: Purchase item from e-comm site.

Background:
Given I landed on e-comm page

@tag2
Scenario Outline: Positive testCase to purchase item  
Given Logged in to e-comm site with username <username> and password <password>
When I add the product with name <itemName> from cart
And checkout this product <itemName>
Then confirmation message is "Thankyou for the order."
Examples:
|username           |password     |itemName     |
|shubh2304@gmail.com|Shubham@2304 |ZARA COAT 3  |