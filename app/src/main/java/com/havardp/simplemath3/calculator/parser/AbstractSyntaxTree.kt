package com.havardp.calculator.parser

import com.havardp.calculator.interpreter.nodeVisitor.*
import com.havardp.calculator.lexer.token.Token

/**  https://en.wikipedia.org/wiki/Abstract_syntax_tree  */
abstract class AbstractSyntaxTree(val token: Token){
    abstract fun accept(visitor: RewriteVisitor): AbstractSyntaxTree
    abstract fun accept(visitor: PrettyPrintVisitor): String
    abstract fun accept(visitor: PrettyPrintLatexVisitor): String
    abstract fun accept(visitor: PrintGraphTreeVisitor)

    abstract fun equals(otherTree: AbstractSyntaxTree): Boolean
    abstract fun containsVariable(): Boolean
}

class BinaryOperatorNode(token: Token, val left: AbstractSyntaxTree, val right: AbstractSyntaxTree): AbstractSyntaxTree(token){
    override fun accept(visitor: RewriteVisitor): AbstractSyntaxTree = visitor.visit(this)
    override fun accept(visitor: PrettyPrintVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrettyPrintLatexVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrintGraphTreeVisitor) = visitor.visit(this)

    override fun equals(otherTree: AbstractSyntaxTree): Boolean = otherTree is BinaryOperatorNode && token.value == otherTree.token.value && left.equals(otherTree.left) && right.equals(otherTree.right)

    override fun containsVariable(): Boolean = left.containsVariable() || right.containsVariable()
}

class UnaryOperatorNode(token: Token, val middle: AbstractSyntaxTree): AbstractSyntaxTree(token){
    override fun accept(visitor: RewriteVisitor): AbstractSyntaxTree = visitor.visit(this)
    override fun accept(visitor: PrettyPrintVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrettyPrintLatexVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrintGraphTreeVisitor) = visitor.visit(this)

    override fun equals(otherTree: AbstractSyntaxTree): Boolean = otherTree is UnaryOperatorNode && token.value == otherTree.token.value && middle.equals(otherTree.middle)

    override fun containsVariable(): Boolean = middle.containsVariable()
}

class OperandNode(token: Token): AbstractSyntaxTree(token){
    override fun accept(visitor: RewriteVisitor): AbstractSyntaxTree = visitor.visit(this)
    override fun accept(visitor: PrettyPrintVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrettyPrintLatexVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrintGraphTreeVisitor) = visitor.visit(this)

    override fun equals(otherTree: AbstractSyntaxTree): Boolean = otherTree is OperandNode && token.value == otherTree.token.value

    override fun containsVariable(): Boolean = false
}

class VariableNode(token: Token): AbstractSyntaxTree(token){
    override fun accept(visitor: RewriteVisitor): AbstractSyntaxTree = visitor.visit(this)
    override fun accept(visitor: PrettyPrintVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrettyPrintLatexVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrintGraphTreeVisitor) = visitor.visit(this)

    override fun equals(otherTree: AbstractSyntaxTree): Boolean = otherTree is VariableNode

    override fun containsVariable(): Boolean = true
}

class ImaginaryNode(token: Token): AbstractSyntaxTree(token){
    override fun accept(visitor: RewriteVisitor): AbstractSyntaxTree = visitor.visit(this)
    override fun accept(visitor: PrettyPrintVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrettyPrintLatexVisitor): String = visitor.visit(this)
    override fun accept(visitor: PrintGraphTreeVisitor) = visitor.visit(this)

    override fun equals(otherTree: AbstractSyntaxTree): Boolean = otherTree is ImaginaryNode

    override fun containsVariable(): Boolean = false
}

