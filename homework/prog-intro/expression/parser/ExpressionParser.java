    package expression.parser;

    import expression.*;

    public class ExpressionParser extends BaseParser implements TripleParser {

        public BaseExpression parse(String s) {
            ready(new StringSource(s));
            return parse();
        }

        public BaseExpression parse() {
            return operation3();
        }

        private BaseExpression operation3() {
            BaseExpression first = operation2();
            while (test('+') || test('-')) {
                String operand = parseOperand();
                BaseExpression second = operation2();
                first = createExpression(operand, first, second);
            }
            return first;
        }

        private BaseExpression operation2() {
            BaseExpression first = operation1();
            while (test('*') || test('/')) {
                String operand = parseOperand();
                BaseExpression second = operation1();
                first = createExpression(operand, first, second);
            }
            return first;
        }

        private BaseExpression operation1() {
            BaseExpression first = operation0();
            first = parseSquare(first);
            while (true) {
                String operand = parseOperand();
                if (!operand.equals("**") && !operand.equals("//")) {
                    goBack(operand.length());
                    break;
                }
                BaseExpression second = operation0();
                first = createExpression(operand, first, second);
            }
            return first;
        }

        private BaseExpression operation0() {
            skipWhitespace();
            char next = take();
            BaseExpression result;
            if (next == '(') {
                result = operation3();
                take();
                skipWhitespace();
                return result;
            }
            if (next == '-') {
                if (test('(') || test(' ')) {
                    BaseExpression val = operation0();
                    val = parseSquare(val);
                    return new UnarMinus(val, true);
                }
                BaseExpression val = operation0();
                return new UnarMinus(val, false);
            }
            String str = parseCharacter(next);
            BaseExpression res;
            if (Character.isDigit(str.charAt(str.length() - 1))) {
                res = new Const(Long.parseLong(str));
            } else {
                res = new Variable(str);
            }
            skipWhitespace();
            return res;
        }

        private String parseCharacter(char next) {
            StringBuilder sb = new StringBuilder();
            if (Character.isDigit(next) || Character.isLetter(next)) {
                sb.append(next);
            }
            while (Character.isDigit(get()) || Character.isLetter(get())) {
                sb.append(take());
            }
            return sb.toString();
        }

        private String parseOperand() {
            StringBuilder sb = new StringBuilder();
            char pred = get();
            while (test('+') || test('-') || test('/') || test('*')) {
                if (sb.isEmpty()) {
                    sb.append(take());
                } else if (test(pred) && (pred == '*' || pred == '/')) {
                    sb.append(take());
                } else {
                    break;
                }
            }
            return sb.toString();
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(get()) || test('\t') || test('\r') || test('\n')) {
                take();
            }
        }

        public BaseExpression createExpression(String operand, BaseExpression first, BaseExpression second){
            return switch (operand) {
                case "+" -> new Add(first, second);
                case "-" -> new Subtract(first, second);
                case "*" -> new Multiply(first, second);
                case "/" -> new Divide(first, second);
                case "//" -> new Log(first, second);
                case "**" -> new Pow(first, second);
                default -> throw new IllegalStateException("Unexpected operand: " + operand);
            };
        }

        public BaseExpression parseSquare(BaseExpression value){
            while (test('²')) {
                take();
                value = new Square(value);
            }
            skipWhitespace();
            return value;
        }
    }
