package com.verizon.springmicroservicedemo.rest;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verizon.springmicroservicedemo.Employee;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/message")
public class RestController {
	@Autowired
	private RabbitTemplate rabbittemp;
	@Value("${msg}")
	private String msg;
	@Value("${server.port}")
	private String port;
	@Autowired
	private DirectExchange directExchange;
	@Autowired
	private TopicExchange topicExchange;
	@Autowired
	private FanoutExchange fanoutExchange;
	@RequestMapping
	public String getMessage() {
		rabbittemp.convertAndSend(directExchange.getName(),"directRoutingKey","This is producer sending message");
		return "Message is:"+msg+"from the port:"+port;
	}
	@RequestMapping("/msg2")
	public String getMessage2() {
		rabbittemp.convertAndSend(directExchange.getName(),"directRoutingKey2","This is producer sending message2");
		return "Message is:"+msg+"from the port:"+port;
	}
	@RequestMapping("/topic/msg")
	public String gettopicMessage() {
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("Pooja");
		rabbittemp.convertAndSend(topicExchange.getName(),"one.topic.send",emp);
		return "Message is:"+msg+"from the port:"+port;
	}
	@RequestMapping("/fanout/msg")
	public String getfanoutMessage() {
		rabbittemp.convertAndSend(fanoutExchange.getName(),"*","This is producer sending message via fanout");
		return "Message is:"+msg+"from the port:"+port;
	}

}
