package com.xdhpx.boot.starter;

/**
 * @ClassName HelloService
 * @Description TODO
 * @Author 郝大龙
 * @Date 2020-01-15 17:21
 */
public class HelloService {

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String sayHello(){
        return "Say : "+this.person;
    }

}
