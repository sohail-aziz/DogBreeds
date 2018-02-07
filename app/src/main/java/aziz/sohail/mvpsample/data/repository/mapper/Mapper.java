package aziz.sohail.mvpsample.data.repository.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Mapper<T1, T2> {

    public abstract T2 map(T1 t1);

    public abstract T1 reverseMap(T2 t2);

    public List<T2> map(List<T1> values) {
        List<T2> result = Collections.EMPTY_LIST;

        if (values != null) {
            result = new ArrayList<>(values.size());

            for (T1 t1 : values) {
                result.add(map(t1));
            }
        }
        return result;
    }

    public List<T1> reverseMap(List<T2> values) {
        List<T1> result = Collections.EMPTY_LIST;

        if (values != null) {
            result = new ArrayList<>(values.size());
            for (T2 t2 : values) {
                result.add(reverseMap(t2));
            }
        }

        return result;
    }
}


