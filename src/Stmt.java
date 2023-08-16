import java.util.List;

abstract class Stmt {
    interface Visitor<R> {
         R visitBlockStmt(Block stmt) throws RuntimeError;
         R visitClassStmt(Class stmt) throws RuntimeError;
         R visitIfStmt(If stmt) throws RuntimeError;
         R visitExpressionStmt(Expression stmt) throws RuntimeError;
         R visitFunctionStmt(Function stmt) throws RuntimeError;
         R visitReturnStmt(Return stmt) throws RuntimeError;
         R visitVarStmt(Var stmt) throws RuntimeError;
         R visitPrintStmt(Print stmt) throws RuntimeError;
         R visitWhileStmt(While stmt) throws RuntimeError;
     }
    static class Block extends Stmt {
         Block(List<Stmt> statements) {
            this.statements = statements;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitBlockStmt(this);
        }

        final List<Stmt> statements;
    }
    static class Class extends Stmt {
         Class(Token name, List<Stmt.Function> methods) {
            this.name = name;
            this.methods = methods;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitClassStmt(this);
        }

        final Token name;
        final List<Stmt.Function> methods;
    }
    static class If extends Stmt {
         If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitIfStmt(this);
        }

        final Expr condition;
        final Stmt thenBranch;
        final Stmt elseBranch;
    }
    static class Expression extends Stmt {
         Expression(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitExpressionStmt(this);
        }

        final Expr expression;
    }
    static class Function extends Stmt {
         Function(Token name, List<Token> params, List<Stmt> body) {
            this.name = name;
            this.params = params;
            this.body = body;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitFunctionStmt(this);
        }

        final Token name;
        final List<Token> params;
        final List<Stmt> body;
    }
    static class Return extends Stmt {
         Return(Token keyword, Expr value) {
            this.keyword = keyword;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitReturnStmt(this);
        }

        final Token keyword;
        final Expr value;
    }
    static class Var extends Stmt {
         Var(Token name, Expr initializer) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitVarStmt(this);
        }

        final Token name;
        final Expr initializer;
    }
    static class Print extends Stmt {
         Print(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitPrintStmt(this);
        }

        final Expr expression;
    }
    static class While extends Stmt {
         While(Expr condition, Stmt body) {
            this.condition = condition;
            this.body = body;
        }

        @Override
        <R> R accept(Visitor<R> visitor) throws RuntimeError {
            return visitor.visitWhileStmt(this);
        }

        final Expr condition;
        final Stmt body;
    }

  abstract <R> R accept(Visitor<R> visitor) throws RuntimeError;
}
