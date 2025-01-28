String description = tempArr[0].trim();
                            if (description.isEmpty()) {
                                 System.out.println("_________________________________\n" +
                                "The description of a todo cannot be empty or whitespace only.\n" +
                                "_________________________________\n");
                            } else {
                                String from = tempArr[1].split(" ", 2)[1];
                                String to = tempArr[2].split(" ", 2)[1];
                                tasks[taskCount] = new Event(description, from, to);
                                String echo = 
                                "_________________________________\n" +
                                "Got it, I've added this task: \n" 
                                + tasks[taskCount].toString() + "\n" +
                                "Now you already have " + (++taskCount) + " tasks.\n" +
                                "_________________________________\n";
                                System.out.println(echo);
                            }