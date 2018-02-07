package aziz.sohail.mvpsample.data.remote;

import java.util.List;


/**
 *  POJO for BreedDetails API
 */
public class ResponseBreedDetails {


    public final String status;
    public final List<String> message;

    public ResponseBreedDetails(String status, List<String> message) {
        this.status = status;
        this.message = message;
    }
}
