import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Evaluator {

    public BiFunction<Boolean, Boolean, Boolean> xor_gate() {
        return (a,b) -> (a!=b);
    }

    public BiFunction<Boolean, Boolean, Boolean> or_gate() {
        return (a, b) -> a ? true : b;
    }

    public BiFunction<Boolean, Boolean, Boolean> and_gate() {
        return (a, b) -> (a == b) ? a : false;
    }

    public Function<Boolean, Boolean> not_gate() {
        return a -> !a;
    }

    // Should return a map with the results stored in map ( use HashMap )
    // Keys are "SUM", "CarryOut"
    public Map<String, Boolean> evaluate_circuit(Boolean a, Boolean b, Boolean carry_in) {
        return new HashMap<String, Boolean>(){{
            put("SUM", xor_gate().apply(xor_gate().apply(a,b), carry_in));
            put("CarryOut", or_gate().apply(
                    and_gate().apply(carry_in, xor_gate().apply(a, b)),
                    and_gate().apply(a, b)
            ));
        }};
    }

}