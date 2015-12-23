package edu.frescoplus.core.app.fresco;

import edu.frescoplus.core.app.fresco.modules.AFresco_Module;
import org.slf4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FS_Parser
{
    private Logger log;

    private ArrayList<AFresco_Module> modules;
    private HashMap<String,String> varTable;

    private final char comment_token = '#';
    private final String end_token = "end";

    private final String event_token  = "event";
    private final String input_token  = "input";
    private final String output_token = "output";
    private final String param_token  = "paramater";

    private Pattern mod_name_re       = Pattern.compile("_(.*)");
    private Pattern mod_attribute_re = Pattern.compile("(.*):(.*)");

    private Pattern action_exp_re     = Pattern.compile("(.*):(.*)");

    // Ternary [lOperand] [operator] [rOperand] ? [val if true] : [val if false]
    // ternary_re (left section) ? (right section)
    // ternary_left_re (lOperand)(operators)(rOperand)
    private Pattern ternary_re       = Pattern.compile("(.*)\\?(.*)");
    private Pattern ternary_left_re  = Pattern.compile("(.*)(==|>|<)(.*)");
    private Pattern ternary_left_inner_re = Pattern.compile("(.*):(.*)");

    private Pattern ternary_right_re = Pattern.compile("(.*),(.*)");

    private Matcher matcher;

    public FS_Parser(Logger log)
    {
        this.log = log;
        modules = new ArrayList<AFresco_Module>();
        varTable = new HashMap<>();
    }

    public ArrayList<AFresco_Module> modFromText(String filename)
    {
        readScriptFile(filename);
        return modules;
    }

    public HashMap<String, String> getVarTable() {
        return varTable;
    }

    private void ParserDBG(String message)
    {
        log.info("PARSER-DBG", message);
    }

    // Create a module from text and push it onto the modules array (seq exec)
    private Scanner parse_module(String moduleName,Scanner fscanner)
    {
        String line;

        while(fscanner.hasNextLine())
        {
            line = fscanner.nextLine();
            if(line.length() > 0)
            {
                // of form IDENTIFIER : VALUE
                matcher = mod_attribute_re.matcher(line);
                if(matcher.find())
                {
                    String attrName  = matcher.group(1).trim();
                    String attrValue = matcher.group(2).trim();

                    ParserDBG(" : Adding "+
                            moduleName+" "+attrName+" "+attrValue);

                    switch(attrName)
                    {
                        case event_token:
                            break;
                        case input_token:
                            break;
                        case output_token:
                            break;
                        case param_token:
                            break;
                        default:
                            ParserDBG("Module Unexpected attribute : "+line);
                            break;
                    }
                    varTable.put(attrName,attrValue);
                }
                // this section removed, conditionals are handled inner module for now.
//                else if(line.trim().equals("action"))
//                {
//                    ParserDBG(" : parse_action : gc "+matcher.groupCount());
//                    fscanner = parse_action(moduleName,fscanner);
//                }
                else if(line.trim().equals(end_token))
                {
                    break;
                }
                else
                {
                    // Erro
                    ParserDBG("Module Unexpected line : "+line);
                }
            }
        }
        return fscanner;
    }
    // TODO : Fresco handles conditional logic within modules themselves (java)
    /*
    * In the summer of 2015 I wanted to add call and eval action operations so that
    * we could "some" ability to do function invocation, (e.g. call is basically goto).
    * What we need is a proper programming language style implementation for this.
    * */
    private Scanner parse_action(String moduleName,Scanner fscanner)
    {
        String line;
        while(fscanner.hasNextLine())
        {
            line = fscanner.nextLine();
            if(line.length()>0)
            {
                matcher = action_exp_re.matcher(line);

                if (matcher.find())
                {
                    String action_type = matcher.group(1).trim();

//					ParserDBG(("DBG ActionMatch : 0 : "+matcher.group(0).trim());
//					ParserDBG(("DBG ActionMatch : 1 : "+matcher.group(1).trim());
//					ParserDBG(("DBG ActionMatch : 2 : "+matcher.group(2).trim());

                    switch(action_type)
                    {
                        case "call":
                        {
                            String callValue = matcher.group(2).trim();
                            //globalTable.addModuleAction(moduleName,new FrescoModuleActionCall(callValue));
                            break;
                        }
                        case "eval":
                        {
                            matcher = ternary_re.matcher(line);
                            if(matcher.find()) // valid ternary expression
                            {
                                String leftVarKey    = null;
                                String rightVarKey   = null;

                                String operator      = null;
                                String actionIfTrue  = null;
                                String actionIfFalse = null;

                                String ternary_left  = matcher.group(1).trim();
                                String ternary_right = matcher.group(2).trim();

                                // Parse conditional
                                matcher = ternary_left_re.matcher(ternary_left);
                                if(matcher.find())
                                {
                                    operator    = matcher.group(2).trim();
                                    rightVarKey = matcher.group(3).trim();

                                    matcher = ternary_left_inner_re.matcher(matcher.group(1).trim());

                                    if(matcher.find())
                                    {
                                        leftVarKey = matcher.group(2).trim();
                                    }
                                    else
                                    {
                                        ParserDBG("failed to parse ternary left inner");
                                    }
                                    ParserDBG("[ACTION LEFT VALUES] "+leftVarKey+" "+operator+" "+rightVarKey);
                                }
                                else
                                {
                                    ParserDBG("failed to parse ternary left");
                                }

                                // Parse eval logic
                                matcher = ternary_right_re.matcher(ternary_right);
                                if(matcher.find())
                                {
                                    actionIfTrue  = matcher.group(1).trim();
                                    actionIfFalse = matcher.group(2).trim();
                                }
                                else
                                {
                                    ParserDBG("failed to parse ternary right");
                                }

                                // PROC Module Action
//                                globalTable.addModuleAction(moduleName,
//                                        new FrescoModuleActionEval(
//                                                leftVarKey,operator,rightVarKey,
//                                                actionIfTrue,
//                                                actionIfFalse));
                            }
                            else
                            {
                                ParserDBG("erro in action, no match for eval");
                            }
                            break;
                        }
                        default:
                            ParserDBG("erro in action, no such action type "+action_type);
                            break;
                    }
                }
                else if(line.trim().equals("end"))
                {
                    break;
                }
                else
                {
                    ParserDBG("ERROR action unexpected line : "+line);
                }
            }
        }
        return fscanner;
    }

    private void readScriptFile(String filename)
    {
        File file = new File(filename);
        try
        {
            String line;
            Scanner fscanner = new Scanner(file);
            while(fscanner.hasNextLine())
            {
                line = fscanner.nextLine();
                if(line.length()>0)
                {
                    // Comments start with #, strip whitespace and check if the first char is #
                    if(line.trim().charAt(0) == comment_token)
                    {
                        //log.info("Comment line "+line);
                    }

                    // Processes all module definitions sequentially.
                    else if(line.startsWith("module_"))
                    {
                        String module_name = null;

                        matcher = mod_name_re.matcher(line);

                        if (matcher.find())
                        {
                            module_name = matcher.group(1).trim();
                            ParserDBG(" : module_name : "+module_name);
                            fscanner = parse_module(module_name,fscanner);
                        }
                    }
                }
            }
            fscanner.close();
        }
        catch (Exception e)
        {
            log.error("PARSER",filename+" : "+e);
        }
    }
}
