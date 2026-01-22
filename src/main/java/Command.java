public enum Command {

    LIST {
        @Override
        public boolean match(String command) {
            return command.equals("list");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.listTasks();
        }
    },

    TODO {
        @Override
        public boolean match(String command) {
            return command.startsWith("todo");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.addToDosTask(command);
        }
    },

    DEADLINE {
        @Override
        public boolean match(String command) {
            return command.startsWith("deadline");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.addDeadlineTask(command);
        }
    },

    EVENT {
        @Override
        public boolean match(String command) {
            return command.startsWith("event");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.addEventTask(command);
        }
    },

    MARK {
        @Override
        public boolean match(String command) {
            return command.startsWith("mark");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.markTask(command);
        }
    },

    UNMARK {
        @Override
        public boolean match(String command) {
            return command.startsWith("unmark");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.unmarkTask(command);
        }
    },

    DELETE {
        @Override
        public boolean match(String command) {
            return command.startsWith("delete");
        }

        @Override
        public void execute(String command, Sophon sophon) throws SophonException {
            sophon.deleteTask(command);
        }
    };


    public abstract boolean match(String command);
    public abstract void execute(String command, Sophon sophon) throws SophonException;
}
