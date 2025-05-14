# Solution EmailNotificationSystem

## Task A.

### Answers to questions

>What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.

In the `EmailNotificationSystem` class, there is the external dependency `EmailService` which communicates with external
infrastructure (via SMTP or external API). This can make testing slow, non-deterministic or not possible at all, if the
external system is too complicated to test. Because of that, `EmailService` should be tested with doubles. 

>For the dependencies that should be tested using doubles, should the production code be refactored to make it possible? If so, do the refactoring and implement the tests.

No, it doesn't have to be refactored because it can be tested as it is. 

>What are the disadvantages of using doubles in your tests? Answer with examples from the EmailNotificationSystem class.

We might think that the `notifyUser` method works correctly, i.e. if it throws an `IllegalArgumentException` for invalid
input. But in a real application it could still fail when calling `sendEmail` from the `EmailService` class,
which we have mocked. Since mocks don’t reflect the actual behavior of systems, we would additionally need to write
integration tests to verify that the code works correctly with external email servers or APIs.


### Unit tests

* `testNotifyUserValidInput`: Tests the happy case, where an E-Mail and a message is given.
* `testNotifyUserNullEmail`: Tests the case, where no E-Mail is given (null).
* `testNotifyUserEmptyEmail`: Tests the case, where an empty string is given as E-Mail. 

## Task B.

### TDD documentation

We started with writing unit tests for the new method:
* `testBatchNotifyUsers`: Tests the happy case, where a list of E-Mail strings and a message is given.
* `testBatchNotifyUsersNullList`: Tests the case, where a null list is given, in which case an `IllegalArgumentException`
is expected.
* `testBatchNotifyUsersEmptyList`: Tests the case, where an empty list is given, in which case an `IllegalArgumentException`
is expected.
* `testBatchNotifyUsersNullEmail`: Tests the case, where a null E-Mail is given, in which case an `IllegalArgumentException`
is expected. Here we had to create an ArrayList and populate it with a null string/E-Mail, because arguments of the
method `List.of` are annotated with `@NotNull`, so it would throw a `NullPointerException` in any case.
* `testBatchNotifyUsersEmptyEmail`: Tests the case, where an empty string is given as E-Mail, in which case again
an `IllegalArgumentException` is expected. 

The tests failed, as expected.

For the subsequent implementation in `EmailNotificationSystem`, we took inspiration from the `notifyUser` method.
First, we checked if the list is null or empty and threw an `IllegalArgumentException` if it was. Then we wrote a
for-loop iterating over the list of E-Mails and checked if the E-Mail string is null or empty. If it was, we threw an
`IllegalArgumentException` again. Else we called the `sendService` from `EmailService` with the current string and
the message given in the parameters of the method.

After that, we ran the tests again and they all passed. In a next step of the TDD process, the implementation and test
code should be refactored. In our case, we didn't have to refactor the code, because it was already clean, readable and
extensible.

### Answers to questions

>What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.

Again, the external dependency is `EmailService`, which should be tested with doubles, because it communicates with
external infrastructure (via SMTP or external API). As mentioned before, this can make testing slow, non-deterministic or
not possible at all. Because of that, `EmailService` should be tested with doubles.

>For the dependencies that should be tested using doubles, should the production code be refactored to make it possible? If so, do the refactoring and implement the tests.

No, it doesn't have to be refactored because it can be tested as it is. Furthermore, since we applied TDD and wrote
tests before implementing the code, we already had a clean and testable code.

>What are the disadvantages of using doubles in your tests? Answer with examples from the EmailNotificationSystem class.

The same disadvantages as in Task A apply here.