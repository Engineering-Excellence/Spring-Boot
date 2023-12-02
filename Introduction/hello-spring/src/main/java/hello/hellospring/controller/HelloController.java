package hello.hellospring.controller;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "")
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(@NonNull Model model) {
        model.addAttribute("data", "spring!!");
        return "hello"; // templates 폴더 아래의 hello.html 파일을 찾아서 렌더링
    }

    @GetMapping(value = "/hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, @NonNull Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @ResponseBody   // viewResolver가 뷰를 찾지 않고 HttpMessageConverter가 대신 동작하여 HTTP 메시지 바디에 내용을 직접 입력
    @GetMapping(value = "/hello-string")
    public String helloString(@RequestParam(name = "name") String name) {
        return "hello " + name; // 문자를 반환하면 StringHttpMessageConverter 동작
    }

    @ResponseBody
    @GetMapping(value = "/hello-api")
    public Hello helloApi(@RequestParam(name = "name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체를 반환하면 MappingJackson2HttpMessageConverter 동작: Jackson 라이브러리가 변환해서 바디에 담아줌
    }

    static class Hello {

        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }
}
