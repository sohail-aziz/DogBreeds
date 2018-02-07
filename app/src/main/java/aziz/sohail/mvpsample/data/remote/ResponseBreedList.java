package aziz.sohail.mvpsample.data.remote;

import java.util.List;


/**
 *  POJO for BreedList REST API
 */
public class ResponseBreedList {

    public final String status;
    public final List<String> message;

    public ResponseBreedList(String status, List<String> message) {
        this.status = status;
        this.message = message;
    }
}
