package org.frankframework.backend.endpoints;


import org.frankframework.backend.models.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<TestResponse> test() {
        return ResponseEntity.ok(new TestResponse("Hello World!"));
    }
}
