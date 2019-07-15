import java.util.*;

public class MainClass
{

    public static void main(String []args)
    {
        /*int a=5;
        double b=6.5;
        float c=1.2f;
        short d=155;
        byte e=127;
        //a+a*(b+d*(e+c)%b)*((((((-a)))))+-b*c+e*e)%a
        //a+a*(b+d*(e+c)%b)*((((((-a)))))+-b*c+e*e)%a=?
        System.out.println(a+a*(b+d*(e+c)%b)*((((((-a)))))+-b*c+e*e)%a);*/
        LShell shell=new LShell(false);
        shell.run();
    }
}


enum VAR_TYPE
{
    var_type_char,
    var_type_byte,
    var_type_short,
    var_type_int,
    var_type_long,
    var_type_float,
    var_type_double,
    var_type_null
}

class VariableException extends  Exception
{
    String msg,extra;
    VariableException(String m,String mm)
    {
        msg=m;
        extra=mm;
    }
}

class ExpressionException extends  Exception
{
    String msg,extra;
    ExpressionException(String m,String mm)
    {
        msg=m;
        extra=mm;
    }
}

class variable implements Comparable<variable>
{

    private VAR_TYPE type=VAR_TYPE.var_type_null;
    private Number value=null;

    public String toString()
    {
        StringBuilder temp=new StringBuilder();
        if(value==null)temp.append("wrong - variable unassigned");
        else
            if(type==VAR_TYPE.var_type_double||type==VAR_TYPE.var_type_float)
                temp.append(String.format("%.2f",value.doubleValue()));
            else
                temp.append(value);
        return temp.toString();
    }

    @Override
    public int compareTo(variable v)throws NullPointerException,ClassCastException
    {
        return 0;
    }
    variable(){}
    static variable plus(variable a,variable b)
    {
        VAR_TYPE resType=(a.type.compareTo(b.type)>0)?a.type:b.type;
        variable res=new variable();
        res.type=resType;
        switch(resType)
        {
            case var_type_byte:
            case var_type_char:
                res.value=a.value.byteValue()+b.value.byteValue();
                break;
            case var_type_short:
                res.value=a.value.shortValue()+b.value.shortValue();
                break;
            case var_type_int:
                res.value=a.value.intValue()+b.value.intValue();
                break;
            case var_type_long:
                res.value=a.value.longValue()+b.value.longValue();
                break;
            case var_type_float:
                res.value=a.value.floatValue()+b.value.floatValue();
                break;
            case var_type_double:
                res.value=a.value.doubleValue()+b.value.doubleValue();
                break;
            default:return null;
        }
        return res;
    }
    static variable sub(variable a,variable b)
    {
        VAR_TYPE resType=(a.type.compareTo(b.type)>0)?a.type:b.type;
        variable res=new variable();
        res.type=resType;
        switch(resType)
        {
            case var_type_byte:
            case var_type_char:
                res.value=a.value.byteValue()-b.value.byteValue();
                break;
            case var_type_short:
                res.value=a.value.shortValue()-b.value.shortValue();
                break;
            case var_type_int:
                res.value=a.value.intValue()-b.value.intValue();
                break;
            case var_type_long:
                res.value=a.value.longValue()-b.value.longValue();
                break;
            case var_type_float:
                res.value=a.value.floatValue()-b.value.floatValue();
                break;
            case var_type_double:
                res.value=a.value.doubleValue()-b.value.doubleValue();
                break;
            default:return null;
        }
        return res;
    }
    static variable mul(variable a,variable b)
    {
        VAR_TYPE resType=(a.type.compareTo(b.type)>0)?a.type:b.type;
        variable res=new variable();
        res.type=resType;
        switch(resType)
        {
            case var_type_byte:
            case var_type_char:
                res.value=a.value.byteValue()*b.value.byteValue();
                break;
            case var_type_short:
                res.value=a.value.shortValue()*b.value.shortValue();
                break;
            case var_type_int:
                res.value=a.value.intValue()*b.value.intValue();
                break;
            case var_type_long:
                res.value=a.value.longValue()*b.value.longValue();
                break;
            case var_type_float:
                res.value=a.value.floatValue()*b.value.floatValue();
                break;
            case var_type_double:
                res.value=a.value.doubleValue()*b.value.doubleValue();
                break;
            default:return null;
        }
        return res;
    }
    static variable div(variable a,variable b)throws ExpressionException
    {
        try
        {
            VAR_TYPE resType=(a.type.compareTo(b.type)>0)?a.type:b.type;
            variable res=new variable();
            res.type=resType;
            switch(resType)
            {
                case var_type_byte:
                case var_type_char:
                    res.value=a.value.byteValue()/b.value.byteValue();
                    break;
                case var_type_short:
                    res.value=a.value.shortValue()/b.value.shortValue();
                    break;
                case var_type_int:
                    res.value=a.value.intValue()/b.value.intValue();
                    break;
                case var_type_long:
                    res.value=a.value.longValue()/b.value.longValue();
                    break;
                case var_type_float:
                    res.value=a.value.floatValue()/b.value.floatValue();
                    break;
                case var_type_double:
                    res.value=a.value.doubleValue()/b.value.doubleValue();
                    break;
                default:return null;
            }
            return res;
        }
        catch(ArithmeticException e)
        {
            throw new ExpressionException("wrong - error expression","ArithmeticException:"+e.getMessage());
        }
    }
    static variable pow(variable a,variable b)
    {
        variable res=new variable();
        res.type=VAR_TYPE.var_type_double;
        res.value=Math.pow(a.value.doubleValue(),b.value.doubleValue());
        return res;
    }
    static variable mode(variable a,variable b)
    {
        VAR_TYPE resType=(a.type.compareTo(b.type)>0)?a.type:b.type;
        variable res=new variable();
        res.type=resType;
        switch(resType)
        {
            case var_type_byte:
            case var_type_char:
                res.value=a.value.byteValue()%b.value.byteValue();
                break;
            case var_type_short:
                res.value=a.value.shortValue()%b.value.shortValue();
                break;
            case var_type_int:
                res.value=a.value.intValue()%b.value.intValue();
                break;
            case var_type_long:
                res.value=a.value.longValue()%b.value.longValue();
                break;
            case var_type_float:
                res.value=a.value.floatValue()%b.value.floatValue();
                break;
            case var_type_double:
                res.value=a.value.doubleValue()%b.value.doubleValue();
                break;
            default:return null;
        }
        return res;
    }
    static variable assign(variable a,variable b)throws ExpressionException,VariableException
    {
        variable res=new variable();
        res.type=b.type;
        if(res.type==VAR_TYPE.var_type_double||res.type==VAR_TYPE.var_type_float)
        {
            if(a.type==VAR_TYPE.var_type_long||
                    a.type==VAR_TYPE.var_type_byte||
                    a.type==VAR_TYPE.var_type_char||
                    a.type==VAR_TYPE.var_type_int||
                    a.type==VAR_TYPE.var_type_short)
                throw new ExpressionException("wrong - error expression","unmatched variable type");
        }
        a.value=res.value=b.value;
        switch(a.type)
        {
            case var_type_int: a.setValue(b.getValue().intValue(),VAR_TYPE.var_type_int);break;
            case var_type_byte:a.setValue(b.getValue().byteValue(),VAR_TYPE.var_type_byte);break;
            case var_type_double:a.setValue(b.getValue().doubleValue(),VAR_TYPE.var_type_double);break;
            case var_type_char:a.setValue(b.getValue().byteValue(),VAR_TYPE.var_type_char);break;
            case var_type_float:a.setValue(b.getValue().floatValue(),VAR_TYPE.var_type_float);break;
            case var_type_long:a.setValue(b.getValue().longValue(),VAR_TYPE.var_type_long);break;
            case var_type_short:a.setValue(b.getValue().shortValue(),VAR_TYPE.var_type_short);break;
            default:
                a.setType(VAR_TYPE.var_type_null);
                throw new VariableException("wrong - variable unassigned","null type");
        }
        Double da=a.getValue().doubleValue(),db=b.getValue().doubleValue();
        Long la=a.getValue().longValue(),lb=b.getValue().longValue();
        /*System.out.println("da:"+da);
        System.out.println("db:"+db);
        System.out.println("la:"+la);
        System.out.println("lb:"+lb);*/
        if(!la.equals(lb)||Math.abs(da-db)>0.00001d)
            throw new ExpressionException("wrong - error expression","overflow");
        return a;
    }

    void setTypeFromString(String t)
    {
        switch(t)
        {
            case "int": this.setType(VAR_TYPE.var_type_int);break;
            case "byte":this.setType(VAR_TYPE.var_type_byte);break;
            case "double":this.setType(VAR_TYPE.var_type_double);break;
            case "char":this.setType(VAR_TYPE.var_type_char);break;
            case "float":this.setType(VAR_TYPE.var_type_float);break;
            case "long":this.setType(VAR_TYPE.var_type_long);break;
            case "short":this.setType(VAR_TYPE.var_type_short);break;
            default:this.setType(VAR_TYPE.var_type_null);break;
        }
    }

    private void setType(VAR_TYPE t)
    {
        if(value!=null)
        {
            Number num=value;
            switch(t)
            {
                case var_type_byte:
                    value=num.byteValue();
                case var_type_short:
                    value=num.shortValue();
                case var_type_int:
                    value=num.intValue();
                case var_type_float:
                    value=num.floatValue();
                case var_type_double:
                    value=num.doubleValue();
                case var_type_char:
                    value=(byte)(int)num;
                case var_type_long:
                    value=num.longValue();
                default:value=null;
            }
        }
        type=t;
    }
    void setValue(Number num,VAR_TYPE t)
    {
        value=num;
        type=t;
    }
    private Number getValue(){return value;}
}

class LShell
{
    private boolean debug;
    private Map<String,variable> varPool=new HashMap<>();
    private boolean running=true;

    LShell(boolean debug)
    {
        this.debug=debug;
    }

    class Expression
    {
        String expression;
        Expression(String text){expression=text.trim();}

        variable getValue()throws ExpressionException,VariableException
        {
            return Process();
        }

        private boolean isOperator(char c)
        {
            switch(c)
            {
                case'+':case'-':case'*':case'/':case'%':case'^': case'=':
                return true;
                default :return false;
            }
        }

        private boolean isBracket(char c)
        {
            return (c=='('||c==')');
        }

        private boolean isNum(char c)
        {
            return '0'<=c&&c<='9';
        }

        private String getNearString(String s,int index)
        {
            int iMin=index-5>0?index-5:0,iMax=index+5<s.length()?index+5:s.length();
            return s.substring(iMin,iMax);
        }

        private variable readItem(String item)throws ExpressionException
        {
            if(item.length()<=0)return null;
            int firstChar=0;
            boolean negative=false;
            if(item.charAt(0)=='-'||item.charAt(0)=='+')
            {
                negative=true;
                firstChar=1;
            }
            if(firstChar==1&&item.length()<=1)
                throw new ExpressionException("wrong - error expression","only operator expression");
            if(item.charAt(firstChar)=='-'||item.charAt(firstChar)=='+'||isNum(item.charAt(firstChar)))
            {
                variable res=new variable();
                try
                {
                    if(item.contains("."))
                    {
                        res.setValue(Double.valueOf(item),VAR_TYPE.var_type_double);
                        if(negative)
                        {
                            variable temp=new variable();
                            temp.setValue(-1.0d,VAR_TYPE.var_type_double);
                            return variable.mul(res,temp);
                        }
                    }
                    else
                    {
                        res.setValue(Long.valueOf(item),VAR_TYPE.var_type_long);
                        if(negative)
                        {
                            variable temp=new variable();
                            temp.setValue(-1,VAR_TYPE.var_type_long);
                            return variable.mul(res,temp);
                        }
                    }
                    return res;
                }
                catch(NumberFormatException e)
                {
                    //System.out.println(e.getMessage());
                    throw new ExpressionException("wrong - error expression","format error:"+e.getMessage());
                }
            }
            else
            {
                //System.out.println(item);
                item=item.substring(firstChar);
                variable res=varPool.get(item);
                if(res==null)throw new ExpressionException("wrong - variable undefined","undefine");
                if(negative)
                {
                    variable temp=new variable();
                    temp.setValue(-1,VAR_TYPE.var_type_long);
                    return variable.mul(res,temp);
                }
                return res;
            }
        }

        variable Process()throws ExpressionException,VariableException
        {
            if(expression.equals(""))return null;

            ArrayList<String>suffix=new ArrayList<>();
            Stack<Character>opStack=new Stack<>();

            Character read;

            for(int i=0;i<expression.length();++i)
            {
                read=expression.charAt(i);

                while(read==' ')read=expression.charAt(++i);

                while(read=='(')
                {
                    opStack.push(read);
                    if(i==expression.length())
                        throw new ExpressionException("wrong - error expression","end with left bracket");
                    read=expression.charAt(++i);
                }

                while(read==' ')read=expression.charAt(++i);

                StringBuilder itemBuilder=new StringBuilder();
                boolean signed=false;
                while(i<expression.length())
                {
                    read=expression.charAt(i);
                    if(!signed)
                    {
                        if(read=='-'||read=='+')
                        {
                            itemBuilder.append(read);
                            ++i;
                            continue;
                        }
                        signed=true;
                    }
                    if(read==' '||isOperator(read)||isBracket(read))break;
                    itemBuilder.append(read);
                    ++i;
                }

                suffix.add(itemBuilder.toString());

                while(read==' ')read=expression.charAt(++i);

                while(i<expression.length()&&read==')')
                {
                    read=expression.charAt(i);
                    boolean isSuit=false;
                    while(!opStack.empty()&&!isSuit)
                    {
                        Character top = opStack.peek();
                        opStack.pop();
                        if (top == '(')
                            isSuit = true;
                        else
                            suffix.add(top.toString());
                    }
                    if(!isSuit)
                        throw new ExpressionException("wrong - error expression","unsuited right bracket:"+getNearString(expression,i));
                    ++i;
                    if(i<expression.length())read=expression.charAt(i);
                }

                if(i>=expression.length())break;

                while(read==' ')read=expression.charAt(++i);
                //System.out.println(read);
                if(isOperator(read))
                {
                    if(opStack.empty())
                        opStack.push(read);
                    else
                    {
                        int priority=0;
                        switch(read)
                        {
                            case('='):priority=0;break;
                            case('+'):case('-'):priority=1;break;
                            case('*'):case('/'):case('%'):priority=2;break;
                            case('^'):priority=3;break;
                            default:break;
                        }

                        while(!opStack.empty())
                        {
                            int topPriority;
                            Character topRead=opStack.peek();
                            switch(topRead)
                            {
                                case('='):topPriority=0;break;
                                case('+'):case('-'):topPriority=1;break;
                                case('*'):case('/'):case('%'):topPriority=2;break;
                                case('^'):topPriority=3;break;
                                default:topPriority=0;break;
                            }
                            if((priority!=3||topPriority!=3)&&(priority!=0||topPriority!=0)&&priority<=topPriority)
                            {
                                opStack.pop();
                                suffix.add(topRead.toString());
                            }
                            else break;
                        }
                        opStack.push(read);
                    }
                }
                else
                    throw new ExpressionException("wrong - error expression","unknown:"+getNearString(expression,i));

                if(i+1>=expression.length())
                    throw new ExpressionException("wrong - error expression","end with operator:"+getNearString(expression,i));
            }


            while(!opStack.empty())
            {
                read=opStack.peek();
                if(!isOperator(read))
                    throw new ExpressionException("wrong - error expression","unsuited left bracket");
                suffix.add(read.toString());
                opStack.pop();
            }

            if(suffix.size()<=0)throw new ExpressionException("wrong - error expression","expression error:"+expression);

           // for(String s :suffix)System.out.println(s);

            Stack<variable> resStack=new Stack<>();

            for(String s:suffix)
            {
                if(s.length()<=0)continue;
                if(isOperator(s.charAt(0))&&s.length()==1)
                {
                    try
                    {
                        switch(s.charAt(0))
                        {
                            case'=' :
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.assign(a,b));
                                break;
                            }
                            case'+' :
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.plus(a,b));
                                break;
                            }
                            case('-'):
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.sub(a,b));
                                break;
                            }
                            case('*'):
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.mul(a,b));
                                break;
                            }
                            case('/'):
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.div(a,b));
                                break;
                            }
                            case('%'):
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.mode(a,b));
                                break;
                            }
                            case('^'):
                            {
                                variable a,b;
                                b=resStack.peek();resStack.pop();
                                a=resStack.peek();resStack.pop();
                                resStack.push(variable.pow(a,b));
                                break;
                            }
                            default:break;
                        }
                    }
                    catch(NullPointerException e)
                    {
                        throw new ExpressionException("wrong - variable unassigned","null object");
                    }
                    catch(EmptyStackException e)
                    {
                        throw new ExpressionException("wrong - error expression","empty stack error");
                    }
                }
                else
                    resStack.push(readItem(s));
            }
            return resStack.peek();
        }
    }

    void  run()
    {
        Scanner scanner=new Scanner(System.in);

        StringBuilder exprBuild=new StringBuilder();
        while(running)
        {
            String line=scanner.nextLine();
            line=line+'\n';
            exprBuild.append(' ');
            for(int i=0;i<line.length();++i)
            {
                char c=line.charAt(i);
                if(c==';'||c=='?'||c=='\n')
                {
                    String expression=exprBuild.toString();
                    exprBuild.delete(0,exprBuild.length());

                    //System.out.println("read:"+expression);

                    try
                    {
                        String s=readInput(expression);
                        //System.out.println(s);
                        process(s);
                    }
                    catch(ExpressionException e )
                    {
                        if(debug)
                            System.out.println(e.msg+':'+e.extra);
                        else
                            System.out.println(e.msg);
                        running=false;
                    }
                    catch(VariableException e )
                    {
                        if(debug)
                            System.out.println(e.msg+':'+e.extra);
                        else
                            System.out.println(e.msg);
                        running=false;
                    }
                    if(c=='?')running=false;
                }
                else
                    exprBuild.append(c);

            }
        }
    }

    private boolean isVariableName(String s)
    {
        if(s.length()==0)return false;
        char c=s.charAt(0);
        if(c=='_'||('a'<=c&&c<='z')||('A'<=c&&c<='Z'))
        {
            boolean res=true;
            for(int i=1;res&&i<s.length();++i)
            {
                c=s.charAt(i);
                if(!(c=='_'||('a'<=c&&c<='z')||('A'<=c&&c<='Z')||('0'<=c&&c<='9')))
                    res=false;
            }
            return res;
        }
        else
            return false;
    }

    private void printVarList()
    {
        if(varPool.size()>0)
        {
            StringBuilder msg=new StringBuilder();
            for (Map.Entry<String,variable> entry : varPool.entrySet())
            {
                StringBuilder each=new StringBuilder();
                each.append(entry.getKey());
                each.append(":");
                each.append(entry.getValue());
                each.append("\n");
                msg.append(each);
            }
            System.out.println(msg);
        }
        else
            System.out.println("there is no variable");

    }

    private String readInput(String input)
    {
        return input.trim().replace("\n","").replaceAll("\\s{2,}", " ");
    }

    private void process(String statement)throws VariableException,ExpressionException
    {
        if(statement.equals(""))return;

        if(statement.charAt(0)=='@')
        {
            statement=statement.substring(1).trim();
            String []statements=statement.split(" ");
            switch(statements[0])
            {
                case "exit":
                case "EXIT":
                    running=false;
                    break;
                case "VARIABLE_LIST":
                case "variable_list":printVarList();

                    break;
                case "DELETE":
                case "delete":
                    if(statements.length<=1)throw new ExpressionException("wrong - error expression","can not delete empty variable name");
                    statements[1]=statements[1].trim();
                    variable v=varPool.get(statements[1]);
                    if(v==null)throw new ExpressionException("wrong - error expression","variable not found:"+statements[1]);
                    varPool.remove(statements[1]);
                    break;
                default: throw new ExpressionException("wrong - error expression","unknown:"+statement);
            }
        }
        else
        {
            switch(getFirstItem(statement))
            {
                case "int":case "byte":case "double":
                case "char":case "float":case "long":case "short":
                variableDeclare(statement);
                break;
                default:
                    int i=statement.length()-1;
                    if(statement.charAt(i)=='=')
                    {
                        statement=statement.substring(0,statement.length()-1).trim();
                        Expression e=new Expression(statement);
                        System.out.println(e.getValue());
                    }
                    else
                    {
                        Expression e=new Expression(statement);
                        e.getValue();
                    }
                    break;
            }
        }
    }


    private void variableDeclare(String expression)throws VariableException,ExpressionException
    {

        String[]sub=expression.split(" ",2);

        if(sub.length<=1)
            throw new ExpressionException("wrong - variable undefined","not found variable");

        switch(sub[0])
        {
            case "int": case "byte": case "double":
            case "char": case "float": case "long": case "short":break;
            default:throw new VariableException("wrong - error expression","unknown type:"+sub[0]);
        }

        sub[1]=sub[1].trim();


        if(sub[1].length()==0)
            throw new VariableException("wrong - variable undefined","not found variable name");
        if(sub[1].charAt(sub[1].length()-1)==',')
            throw new ExpressionException("wrong - error expression","unnecessary ,");

        String[] items=sub[1].split(",");
        for(String it:items)
        {
            it=it.trim();

            String[] parts=it.split("=",2);
            parts[0]=parts[0].trim();
            if(isVariableName(parts[0]))
            {
                if(varPool.get(parts[0])!=null)
                    throw new ExpressionException("wrong - error expression","declared name:");
            }
            else
                throw new VariableException("wrong - variable undefined","not a name");


            if(parts.length==2)
            {
                if(parts[1].equals(""))
                    throw new ExpressionException("wrong - error expression","empty expression");
                Expression assExp=new Expression(parts[1]);
                variable res=new variable(),read=assExp.Process();
                res.setTypeFromString(sub[0]);
                variable.assign(res,read);
                varPool.put(parts[0],res);
            }
            else
            {
                variable v=new variable();
                v.setTypeFromString(sub[0]);
                varPool.put(parts[0],v);
            }

        }

    }

    private String getFirstItem(String expression)
    {
        StringBuilder res=new StringBuilder();
        for(int i =0;i< expression.length();++i)
        {
            char c=expression.charAt(i);
            if(c==' ')
                break;
            else
                res.append(c);
        }
        return res.toString();
    }

}
