package tech.coordinates.codereader.structure;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/13.
 */
public class TreeCreater {

    public LinkedList<SyntaxNode> all_nodes = new LinkedList<>();

    private SyntaxNode package_control_public;
    private SyntaxNode package_control_private;
    private SyntaxNode package_control_protected;
    private SyntaxNode modifier_abstract;
    private SyntaxNode modifier_final;
    private SyntaxNode modifier_static;
    private SyntaxNode modifier_class;
    private SyntaxNode modifier_implements;
    private SyntaxNode modifier_extends;
    private SyntaxNode modifier_interface;
    private SyntaxNode routine_control_break;
    private SyntaxNode routine_control_continue;
    private SyntaxNode routine_control_return;
    private SyntaxNode routine_control_do;
    private SyntaxNode routine_control_while;
    private SyntaxNode routine_control_for;
    private SyntaxNode routine_control_if;
    private SyntaxNode routine_control_else;
    private SyntaxNode routine_control_switch;
    private SyntaxNode routine_control_case;
    private SyntaxNode routine_control_default;
    private SyntaxNode error_handle_try;
    private SyntaxNode error_handle_catch;
    private SyntaxNode error_handle_throw;
    private SyntaxNode package_import;
    private SyntaxNode package_package;

    private SyntaxNode variable_link_super;
    private SyntaxNode variable_link_this;
    private SyntaxNode variable_link_void;

    private SyntaxNode package_name;
    private SyntaxNode import_package_name;
    private SyntaxNode explain;
    //    private SyntaxNode return_type;
    private SyntaxNode variable_name;
    private SyntaxNode class_name;
    private SyntaxNode function_name;
    private SyntaxNode interface_name;
    private SyntaxNode define_new;

    private SyntaxNode operate_assignment;

    //四类八种
    private SyntaxNode basic_type_int;
    private SyntaxNode basic_type_float;
    private SyntaxNode basic_type_double;
    private SyntaxNode basic_type_boolean;
    private SyntaxNode basic_type_char;
    private SyntaxNode basic_type_byte;
    private SyntaxNode basic_type_short;
    private SyntaxNode basic_type_long;

    //循环条件
    private SyntaxNode if_condition;
    private SyntaxNode while_condition;
    private SyntaxNode switch_select;

    private SyntaxNode undefine;

    public TreeCreater() {
        init();
    }

    //创建java语法树
    public LinkedList<SyntaxNode> createSyntaxTree() {
        LinkedList<SyntaxNode> syntax_forest = new LinkedList<>(all_nodes);
        //package control
        for (int i = 0; i < syntax_forest.size(); i++) {
            if (all_nodes.get(i).getTag() == WordProperty.KEY_WORD_PACKAGE_CONTROL) {
//                all_nodes.get(i).getChildNode().add(class_name);
                all_nodes.get(i).getChildNode().add(modifier_abstract);
                all_nodes.get(i).getChildNode().add(modifier_class);
                all_nodes.get(i).getChildNode().add(modifier_final);
                all_nodes.get(i).getChildNode().add(modifier_interface);
                all_nodes.get(i).getChildNode().add(modifier_static);
                all_nodes.get(i).getChildNode().add(variable_link_void);
                all_nodes.get(i).getChildNode().add(import_package_name);
                for (SyntaxNode n : all_nodes) {
                    if (n.getTag() == WordProperty.TYPE_NAME) {
                        all_nodes.get(i).getChildNode().add(n);
                    }
                }
            }
        }
        //abstract
        modifier_abstract.getChildNode().add(modifier_interface);
        modifier_abstract.getChildNode().add(modifier_class);
//        modifier_abstract.getChildNode().add(return_type);
        for (SyntaxNode n : all_nodes) {
            if (n.getTag() == WordProperty.TYPE_NAME) {
                modifier_abstract.getChildNode().add(n);
            }
        }
        //return type
//        return_type.getChildNode().add(function_name);
        //class
        modifier_class.getChildNode().add(class_name);
        modifier_class.getChildNode().add(import_package_name);
        //final
        modifier_final.getChildNode().add(class_name);
        modifier_final.getChildNode().add(import_package_name);
        modifier_final.getChildNode().add(modifier_class);
//        modifier_final.getChildNode().add(return_type);
        for (SyntaxNode n : all_nodes) {
            if (n.getTag() == WordProperty.TYPE_NAME) {
                modifier_final.getChildNode().add(n);
            }
        }
        //interface
        modifier_interface.getChildNode().add(interface_name);
        //static
//        modifier_static.getChildNode().add(class_name);
        modifier_static.getChildNode().add(modifier_final);
        modifier_static.getChildNode().add(modifier_class);
        modifier_static.getChildNode().add(variable_link_void);
        for (SyntaxNode n : all_nodes) {
            if (n.getTag() == WordProperty.TYPE_NAME) {
                modifier_static.getChildNode().add(n);
            }
        }
        //class name
        class_name.getChildNode().add(modifier_extends);
        class_name.getChildNode().add(modifier_implements);
        class_name.getChildNode().add(function_name);
        class_name.getChildNode().add(variable_name);
        class_name.getChildNode().add(class_name);
        class_name.getChildNode().add(variable_link_this);
        //基本类型
        for (SyntaxNode n : all_nodes) {
            if (n.getTag() == WordProperty.TYPE_NAME) {
                n.getChildNode().add(function_name);
                n.getChildNode().add(variable_name);
            }
        }
        //extends
        modifier_extends.getChildNode().add(class_name);
        modifier_extends.getChildNode().add(import_package_name);
        //implement
        modifier_implements.getChildNode().add(interface_name);
        //return
        routine_control_return.getChildNode().add(variable_name);
        routine_control_return.getChildNode().add(class_name);
        routine_control_return.getChildNode().add(function_name);
        for (SyntaxNode n : all_nodes) {
            if (n.getTag() == WordProperty.KEY_WORD_VARIABLE_LINK) {
                routine_control_return.getChildNode().add(n);
            }
        }
        //if
        routine_control_if.getChildNode().add(if_condition);
        //else
        routine_control_else.getChildNode().add(routine_control_if);
        //while
        routine_control_while.getChildNode().add(while_condition);
        //switch
        routine_control_switch.getChildNode().add(switch_select);
        //package
        package_package.getChildNode().add(package_name);
        //import
        package_import.getChildNode().add(import_package_name);
        //void
        variable_link_void.getChildNode().add(function_name);
        //super
        variable_link_super.getChildNode().add(function_name);
        //package_name
        package_name.getChildNode().add(package_name);
        //import_package_name
        import_package_name.getChildNode().add(import_package_name);
        import_package_name.getChildNode().add(class_name);
        //this
        variable_link_this.getChildNode().add(function_name);
        variable_link_this.getChildNode().add(variable_name);
        //function_name
        function_name.getChildNode().add(function_name);
        function_name.getChildNode().add(variable_name);
        function_name.getChildNode().add(operate_assignment);
        //variable_name
        variable_name.getChildNode().add(function_name);
        variable_name.getChildNode().add(variable_name);
        variable_name.getChildNode().add(class_name);
        variable_name.getChildNode().add(operate_assignment);
        //case
        routine_control_case.getChildNode().add(function_name);
        routine_control_case.getChildNode().add(variable_name);
        routine_control_case.getChildNode().add(class_name);
//        routine_control_case.getChildNode().add(import_package_name);
        //switch
        //new
        define_new.getChildNode().add(class_name);
        define_new.getChildNode().add(import_package_name);
        //operator assignment
        operate_assignment.getChildNode().add(define_new);
        operate_assignment.getChildNode().add(function_name);
        operate_assignment.getChildNode().add(variable_name);
        operate_assignment.getChildNode().add(class_name);
        operate_assignment.getChildNode().add(import_package_name);
        //interface name
        interface_name.getChildNode().add(interface_name);
        //throw
        error_handle_throw.getChildNode().add(define_new);
        //if condition
        if_condition.getChildNode().add(undefine);
        //while condition

        return syntax_forest;
    }

    //实例化节点
    private void init() {
        package_control_private = new SyntaxNode();
        package_control_private.setCouldStart(true);
        package_control_private.setCouldEnd(false);
        package_control_private.setName("private");
        package_control_private.setTag(WordProperty.KEY_WORD_PACKAGE_CONTROL);
        all_nodes.add(package_control_private);
        package_control_public = new SyntaxNode();
        package_control_public.setCouldStart(true);
        package_control_public.setCouldEnd(false);
        package_control_public.setTag(WordProperty.KEY_WORD_PACKAGE_CONTROL);
        package_control_public.setName("public");
        all_nodes.add(package_control_public);
        package_control_protected = new SyntaxNode();
        package_control_protected.setCouldStart(true);
        package_control_protected.setCouldEnd(false);
        package_control_protected.setTag(WordProperty.KEY_WORD_PACKAGE_CONTROL);
        package_control_protected.setName("protected");
        all_nodes.add(package_control_protected);
        modifier_static = new SyntaxNode();
        modifier_static.setCouldEnd(true);
        modifier_static.setCouldEnd(false);
        modifier_static.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_static.setName("static");
        all_nodes.add(modifier_static);
        modifier_abstract = new SyntaxNode();
        modifier_abstract.setCouldStart(true);
        modifier_abstract.setCouldEnd(false);
        modifier_abstract.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_abstract.setName("abstract");
        all_nodes.add(modifier_abstract);
        modifier_class = new SyntaxNode();
        modifier_class.setCouldStart(true);
        modifier_class.setCouldEnd(false);
        modifier_class.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_class.setName("class");
        all_nodes.add(modifier_class);
        modifier_final = new SyntaxNode();
        modifier_final.setCouldStart(true);
        modifier_final.setCouldEnd(false);
        modifier_final.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_final.setName("final");
        all_nodes.add(modifier_final);
        modifier_implements = new SyntaxNode();
        modifier_implements.setCouldStart(false);
        modifier_implements.setCouldStart(false);
        modifier_implements.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_implements.setName("implements");
        all_nodes.add(modifier_implements);
        modifier_interface = new SyntaxNode();
        modifier_interface.setCouldStart(true);
        modifier_interface.setCouldEnd(false);
        modifier_interface.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_interface.setName("interface");
        all_nodes.add(modifier_interface);
        modifier_extends = new SyntaxNode();
        modifier_extends.setCouldStart(false);
        modifier_extends.setCouldEnd(false);
        modifier_extends.setTag(WordProperty.KEY_WORD_MODIFIER);
        modifier_extends.setName("extends");
        all_nodes.add(modifier_extends);
        routine_control_break = new SyntaxNode();
        routine_control_break.setCouldStart(true);
        routine_control_break.setCouldEnd(true);
        routine_control_break.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_break.setName("break");
        all_nodes.add(routine_control_break);
        routine_control_case = new SyntaxNode();
        routine_control_case.setCouldStart(true);
        routine_control_case.setCouldEnd(false);
        routine_control_case.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_case.setName("case");
        all_nodes.add(routine_control_case);
        routine_control_continue = new SyntaxNode();
        routine_control_continue.setCouldStart(true);
        routine_control_continue.setCouldEnd(true);
        routine_control_continue.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_continue.setName("continue");
        all_nodes.add(routine_control_continue);
        routine_control_default = new SyntaxNode();
        routine_control_default.setCouldStart(true);
        routine_control_default.setCouldEnd(false);
        routine_control_default.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_default.setName("default");
        all_nodes.add(routine_control_default);
        routine_control_do = new SyntaxNode();
        routine_control_do.setCouldStart(true);
        routine_control_do.setCouldEnd(false);
        routine_control_do.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_do.setName("do");
        all_nodes.add(routine_control_do);
        routine_control_else = new SyntaxNode();
        routine_control_else.setCouldStart(true);
        routine_control_else.setCouldEnd(false);
        routine_control_else.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_else.setName("else");
        all_nodes.add(routine_control_else);
        routine_control_if = new SyntaxNode();
        routine_control_if.setCouldStart(true);
        routine_control_if.setCouldEnd(false);
        routine_control_if.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_if.setName("if");
        all_nodes.add(routine_control_if);
        routine_control_switch = new SyntaxNode();
        routine_control_switch.setCouldStart(true);
        routine_control_switch.setCouldEnd(false);
        routine_control_switch.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_switch.setName("switch");
        all_nodes.add(routine_control_switch);
        routine_control_return = new SyntaxNode();
        routine_control_return.setCouldStart(true);
        routine_control_return.setCouldEnd(false);
        routine_control_return.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_return.setName("return");
        all_nodes.add(routine_control_return);
        routine_control_for = new SyntaxNode();
        routine_control_for.setCouldStart(true);
        routine_control_for.setCouldEnd(false);
        routine_control_for.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_for.setName("for");
        all_nodes.add(routine_control_for);
        routine_control_while = new SyntaxNode();
        routine_control_while.setCouldStart(true);
        routine_control_while.setCouldEnd(false);
        routine_control_while.setTag(WordProperty.KEY_WORD_ROUTINE_CONTROL);
        routine_control_while.setName("while");
        all_nodes.add(routine_control_while);
        error_handle_catch = new SyntaxNode();
        error_handle_catch.setCouldStart(true);
        error_handle_catch.setCouldEnd(false);
        error_handle_catch.setTag(WordProperty.KEY_WORD_ERROR_HANDLE);
        error_handle_catch.setName("catch");
        all_nodes.add(error_handle_catch);
        error_handle_try = new SyntaxNode();
        error_handle_try.setCouldStart(true);
        error_handle_try.setCouldEnd(false);
        error_handle_try.setTag(WordProperty.KEY_WORD_ERROR_HANDLE);
        error_handle_try.setName("try");
        all_nodes.add(error_handle_try);
        error_handle_throw = new SyntaxNode();
        error_handle_throw.setCouldStart(true);
        error_handle_throw.setCouldEnd(false);
        error_handle_throw.setTag(WordProperty.KEY_WORD_ERROR_HANDLE);
        error_handle_throw.setName("throw");
        all_nodes.add(error_handle_throw);
        package_import = new SyntaxNode();
        package_import.setCouldStart(true);
        package_import.setCouldEnd(false);
        package_import.setTag(WordProperty.KEY_WORD_PACKAGE);
        package_import.setName("import");
        all_nodes.add(package_import);
        package_package = new SyntaxNode();
        package_package.setCouldStart(true);
        package_package.setCouldEnd(false);
        package_package.setTag(WordProperty.KEY_WORD_PACKAGE);
        package_package.setName("package");
        all_nodes.add(package_package);
        variable_link_super = new SyntaxNode();
        variable_link_super.setCouldStart(true);
        variable_link_super.setCouldEnd(false);
        variable_link_super.setTag(WordProperty.KEY_WORD_VARIABLE_LINK);
        variable_link_super.setName("super");
        all_nodes.add(variable_link_super);
        variable_link_this = new SyntaxNode();
        variable_link_this.setCouldStart(true);
        variable_link_this.setCouldEnd(false);
        variable_link_this.setTag(WordProperty.KEY_WORD_VARIABLE_LINK);
        variable_link_this.setName("this");
        all_nodes.add(variable_link_this);
        variable_link_void = new SyntaxNode();
        variable_link_void.setCouldStart(true);
        variable_link_void.setCouldEnd(true);
        variable_link_void.setTag(WordProperty.KEY_WORD_VARIABLE_LINK);
        variable_link_void.setName("void");
        all_nodes.add(variable_link_void);
        variable_name = new SyntaxNode();
        variable_name.setCouldStart(true);
        variable_name.setCouldEnd(true);
        variable_name.setTag(WordProperty.CUSTOM_VARIABLE_NAME);
        all_nodes.add(variable_name);
        function_name = new SyntaxNode();
        function_name.setCouldStart(true);
        function_name.setCouldEnd(false);
        function_name.setTag(WordProperty.CUSTOM_FUNCTION_NAME);
        all_nodes.add(function_name);
        class_name = new SyntaxNode();
        class_name.setCouldStart(true);
        class_name.setCouldEnd(false);
        class_name.setTag(WordProperty.TYPE_NAME);
        all_nodes.add(class_name);

//        return_type = new SyntaxNode();
//        return_type.setCouldStart(true);
//        return_type.setCouldEnd(false);
//        return_type.setTag(WordProperty.TYPE_NAME);
//        all_nodes.add(return_type);

        interface_name = new SyntaxNode();
        interface_name.setCouldStart(true);
        interface_name.setCouldEnd(false);
        interface_name.setTag(WordProperty.INTERFACE_NAME);
        all_nodes.add(interface_name);
//        System.out.println(TreeCreater.all_nodes.size());
        package_name = new SyntaxNode();
        package_name.setCouldStart(false);
        package_name.setCouldEnd(true);
        package_name.setTag(WordProperty.PACKAGE_NAME);
        all_nodes.add(package_name);
        import_package_name = new SyntaxNode();
        import_package_name.setCouldStart(false);
        import_package_name.setCouldEnd(true);
        import_package_name.setTag(WordProperty.IMPORT_PACKAGE_NAME);
        all_nodes.add(import_package_name);
        explain = new SyntaxNode();
        explain.setCouldStart(true);
        explain.setCouldEnd(true);
        explain.setTag(WordProperty.EXPLAIN);
        all_nodes.add(explain);
        basic_type_int = new SyntaxNode();
        basic_type_int.setCouldStart(true);
        basic_type_int.setCouldEnd(false);
        basic_type_int.setTag(WordProperty.TYPE_NAME);
        basic_type_int.setName("int");
        all_nodes.add(basic_type_int);
        basic_type_double = new SyntaxNode();
        basic_type_double.setCouldStart(true);
        basic_type_double.setCouldEnd(false);
        basic_type_double.setTag(WordProperty.TYPE_NAME);
        basic_type_double.setName("double");
        all_nodes.add(basic_type_double);
        basic_type_float = new SyntaxNode();
        basic_type_float.setCouldStart(true);
        basic_type_float.setCouldEnd(false);
        basic_type_float.setTag(WordProperty.TYPE_NAME);
        basic_type_float.setName("float");
        all_nodes.add(basic_type_float);
        basic_type_short = new SyntaxNode();
        basic_type_short.setCouldStart(true);
        basic_type_short.setCouldEnd(false);
        basic_type_short.setTag(WordProperty.TYPE_NAME);
        basic_type_short.setName("short");
        all_nodes.add(basic_type_short);
        basic_type_long = new SyntaxNode();
        basic_type_long.setCouldStart(true);
        basic_type_long.setCouldEnd(false);
        basic_type_long.setTag(WordProperty.TYPE_NAME);
        basic_type_long.setName("long");
        all_nodes.add(basic_type_long);
        basic_type_byte = new SyntaxNode();
        basic_type_byte.setCouldStart(true);
        basic_type_byte.setCouldEnd(false);
        basic_type_byte.setTag(WordProperty.TYPE_NAME);
        basic_type_byte.setName("byte");
        all_nodes.add(basic_type_byte);
        basic_type_char = new SyntaxNode();
        basic_type_char.setCouldStart(true);
        basic_type_char.setCouldEnd(false);
        basic_type_char.setTag(WordProperty.TYPE_NAME);
        basic_type_char.setName("char");
        all_nodes.add(basic_type_char);
        basic_type_boolean = new SyntaxNode();
        basic_type_boolean.setCouldStart(true);
        basic_type_boolean.setCouldEnd(false);
        basic_type_boolean.setTag(WordProperty.TYPE_NAME);
        basic_type_boolean.setName("boolean");
        all_nodes.add(basic_type_boolean);
        define_new = new SyntaxNode();
        define_new.setCouldStart(true);
        define_new.setCouldEnd(false);
        define_new.setTag(WordProperty.DEFINE_NEW);
        define_new.setName("new");
        all_nodes.add(define_new);
        operate_assignment = new SyntaxNode();
        operate_assignment.setCouldStart(false);
        operate_assignment.setCouldEnd(false);
        operate_assignment.setTag(WordProperty.OPERATOR_ASSIGNMENT);
        operate_assignment.setName("=");
        all_nodes.add(operate_assignment);
        if_condition = new SyntaxNode();
        if_condition.setCouldStart(false);
        if_condition.setCouldEnd(true);
        if_condition.setTag(WordProperty.IF_CONDITION);
        all_nodes.add(if_condition);
        while_condition = new SyntaxNode();
        while_condition.setCouldStart(false);
        while_condition.setCouldEnd(true);
        while_condition.setTag(WordProperty.WHILE_CONDITION);
        all_nodes.add(while_condition);
        switch_select = new SyntaxNode();
        switch_select.setCouldStart(false);
        switch_select.setCouldEnd(true);
        switch_select.setTag(WordProperty.SWITCH_SELECT);
        all_nodes.add(switch_select);
        undefine = new SyntaxNode();
        undefine.setCouldStart(true);
        undefine.setCouldEnd(false);
        undefine.setTag(WordProperty.UNDEFINE);
        all_nodes.add(undefine);
    }
}
