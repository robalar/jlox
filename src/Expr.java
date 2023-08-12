import java.util.List;

abstract class Expr {
    interface Visitor<R> {
         R visitAssignExpr(Assign expr) throws RuntimeError;
         R visitBinaryExpr(Binary expr) throws RuntimeError;
         R visitGroupingExpr(Grouping expr) throws RuntimeError;
         R visitLiteralExpr(Literal expr) throws RuntimeError;
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
