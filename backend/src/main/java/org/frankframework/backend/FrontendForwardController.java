package org.frankframework.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontendForwardController {
    // This is to make sure angular routing works by always going to index.html and letting angular handled the path, so refreshing on a subpath works
    @GetMapping({"/frontend/{path:^(?!assets|api|.*\\..*$).*$}", "/frontend"})
    public String forward(@PathVariable String path) {
        return "forward:/frontend/index.html";
    }
}
