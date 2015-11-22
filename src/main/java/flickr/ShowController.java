package flickr;

import de.kasiske.flickrapi.FlickrJsonApi;
import de.kasiske.flickrapi.FlickrJsonFeed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class ShowController {

    FlickrJsonApi flickrJsonApi = new FlickrJsonApi();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

	@RequestMapping(value = "/show/{name:.+}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("show");
		model.addObject("msg", name);

        try {
            FlickrJsonFeed flickrJsonFeed = flickrJsonApi.getResult(name);
            model.addObject("flickrTitle", flickrJsonFeed.getTitle());
            model.addObject("flickrImages", flickrJsonFeed.getItems());
        } catch (IOException ignored) {

        } catch (URISyntaxException e) {}


        return model;
	}

}