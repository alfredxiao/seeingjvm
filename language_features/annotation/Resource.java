import framework.*;

@Author(
    name="Alfred",
    title="Junior Smarty"
)
public class Resource implements Callable {

  @Author(name="Cameron", title="Senior Smarty")
  @AccessControl(allow="jack")
  public String call(String username, String value) {
    return "Greeting from " + username + " to " + value;
  }
}
