package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    //http://localhost:8080/hello경로로 요청이 들어올경우
    public String hello(Model model){
        model.addAttribute("data","hello");
        //resources/templates/hello.html 경로에 있는
        //<p th:text="'안녕하세요. ' + ${data}" >안녕하세요. 손님</p>의 ${data}값을 hello로 바꾸어주겠다.
        return "hello";
        //resources/templates/hello.html 에 값을 보내어 적용시키겠다.
    }
    @GetMapping("hello-mvc")
    //http://localhost:8080/hello-mvc로 요청이 들어올경우
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //params name의 값을 받겠다.
        //http://localhost:8080/hello-mvc?name=spring
        model.addAttribute("name", name);
        return "hello-template";
        //resources/templates/hello-template.html 에 값을 보내어 적용시키겠다.
    }

    @GetMapping("hello-string")
    //http://localhost:8080/hello-string?name=spring
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    //http://localhost:8080/hello-api?name=spring
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //return 값이 객체인 경우 spring 자체적인 조건들로 인해서 JSON 형태로 변경하여 결과값을 내보낸다.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
