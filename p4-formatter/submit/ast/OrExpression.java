package submit.ast;

import java.util.ArrayList;

public class OrExpression implements Expression, Node{
    ArrayList<AndExpression> andExpressions;

    public OrExpression(ArrayList<AndExpression> andExpressions) {
        this.andExpressions = andExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        andExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i<andExpressions.size(); i++){
            builder.append("||");
            andExpressions.get(i).toCminus(builder, prefix);
        }
    }
}
