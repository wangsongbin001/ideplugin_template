package test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *         "version": "4.70.0",
 *         "protocol": "1.0",
 *         "appKey": "1e4e9a461f9b4fb09d6a4ae12c1eca83",
 *         "os": "android",
 *         "dependencies": [{
 */
public class DuBaseline implements Serializable {
    public String version;
    public String protocol;
    public String appKey;
    public String os;
    public ArrayList<DuComponent> dependencies;

}
