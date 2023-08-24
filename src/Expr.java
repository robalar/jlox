import java.util.List;

abstract class Expr {
    interface Visitor<R> {
         R visitAssignExpr(Assign expr) throws RuntimeError;
         R visitBinaryExpr(Binary expr) throws RuntimeError;
         R visitCallExpr(Call expr) throws RuntimeError;
         R visitGetExpr(Get expr) throws RuntimeError;
         R visitSetExpr(Set expr) throws RuntimeError;
         R visitThisExpr(This expr) throws RuntimeError;
         R visitGroupingExpr(Grouping expr) throws RuntimeError;
         R visitLiteralExpr(Literal expr) throws RuntimeError;
         R visitLogicalExpr(Logical expr) throws RuntimeError;
         R visitVariableExpr(Variable expr) throws RuntimeError;
         R visitUnaryExpr(Unary expr) throws RuntimeError;
     }
    static class Assign extends Expr {
         Assign(Token name, Expr value) {
            this.name = name;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitAssignExpr(this);
        }

        final Token name;
        final Expr value;
    }
    static class Binary extends Expr {
         Binary(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitBinaryExpr(this);
        }

        final Expr left;
        final Token operator;
        final Expr right;
    }
    static class Call extends Expr {
         Call(Expr callee, Token paren, List<Expr> arguments) {
            this.callee = callee;
            this.paren = paren;
            this.arguments = arguments;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitCallExpr(this);
        }

        final Expr callee;
        final Token paren;
        final List<Expr> arguments;
    }
    static class Get extends Expr {
         Get(Expr object, Token name) {
            this.object = object;
            this.name = name;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitGetExpr(this);
        }

        final Expr object;
        final Token name;
    }
    static class Set extends Expr {
         Set(Expr object, Token name, Expr value) {
            this.object = object;
            this.name = name;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitSetExpr(this);
        }

        final Expr object;
        final Token name;
        final Expr value;
    }
    static class This extends Expr {
         This(Token keyword) {
            this.keyword = keyword;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitThisExpr(this);
        }

        final Token keyword;
    }
    static class Grouping extends Expr {
         Grouping(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitGroupingExpr(this);
        }

        final Expr expression;
    }
    static class Literal extends Expr {
         Literal(Object value) {
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitLiteralExpr(this);
        }

        final Object value;
    }
    static class Logical extends Expr {
         Logical(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitLogicalExpr(this);
        }

        final Expr left;
        final Token operator;
        final Expr right;
    }
    static class Variable extends Expr {
         Variable(Token name) {
            this.name = name;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitVariableExpr(this);
        }

        final Token name;
    }
    static class Unary extends Expr {
         Unary(Token operator, Expr right) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitUnaryExpr(this);
        }

        final Token operator;
        final Expr right;
    }

  abstract <R> R accept(Visitor<R> visitor) throws RuntimeError;
}
