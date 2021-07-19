# pc0721
ToolRental

As a challenge, and also wanting to make a more "realistic" Tool Rental program that a clerk(s) might use, I did decide to try to do a CLI user interface although the requirement said "The demonstration does not require a user interface" - I took this to mean that it was optional. So I implemented it this way.

Because of the above decision, it actually made creating the JUnit test cases very difficult... I was running into many issues when trying to provide keyboard input like so (excerpt of what I was TRYING to do):

```
String selectedToolInput = "JAKR\r\n9/3/15\r\n5\r\n101";
System.setIn(new ByteArrayInputStream(selectedToolInput.getBytes()));

ToolRental.main(null);
```

Which eventually did not workout as well as I thought it would... I think this is perhaps due to my initial decision to make a CLI based implementation, but I believe JUnit has issues automating input/outpus tests via the embedded Eclipse developer console. Because of this difficulty, I included test case documents in this repository as well (Please see "Tool Rental Test Cases.{PDF, HTML, ORG"}" for those test results and details.

In addition to the passing test cases included in this repository, I have a couple ideas that would make the application more useful if it were ever to be used in the real world:

- Instead of hard-coding the tools (ladder, chansaw, jackhammer, etc) in the Java, perhaps store them in a SQLite DB (or alternative). This will give the program more flexibility if tools need to be added, edited, or removed and won't require a new Java build for every change. Additional interfaces can be made to edit these tools as well if needs be

- In its current state, the application needs to be re-ran upon every new rental agreement generated. A loop either in the Java, or in a shell script will allow the program to continuously be available for clerks (and not have to be physically re-ran). Controls could also be added to re-generate a new Rental Agreement or to stop the application

I really enjoyed working on this project during this last weekend. It was unfortunate that I was not able to get any successful JUnit tests to work with my implementation, but I was able to verify the input and output via my manual test cases, and I believe they fulfil the requirements.