package com.verizon.springmicroservicedemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {
	@Bean(name="directqueue")
	public Queue queue() {
		return new Queue("directqueue"); //queue name for asynchronus or active consumer
	}
	@Bean(name="directqueue2")
	public Queue queue2() {
		return new Queue("directqueue2"); //queue name for polling or lazy consumer
	}
	@Bean(name="topicqueue")
	public Queue topicqueue() {
		return new Queue("topicqueue"); //queue name
	}
	@Bean(name="fanoutqueue1")
	public Queue fanoutqueue1() {
		return new Queue("fanoutqueue1"); //queue name
	}
	@Bean(name="fanoutqueue2")
	public Queue fanoutqueue2() {
		return new Queue("fanoutqueue2"); //queue name
	}
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("directExchange");
	}
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}
	
	@Bean(name="directbinding")
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(directExchange()).with("directRoutingKey");
	}
	@Bean(name="directbinding2")
	public Binding binding2() {
		return BindingBuilder.bind(queue2()).to(directExchange()).with("directRoutingKey2");
	}
	@Bean(name="topicbinding")
	public Binding topicbinding() {
		return BindingBuilder.bind(topicqueue()).to(topicExchange()).with("*.topic.*");
	}
	@Bean(name="fanoutbinding1")
	public Binding fanoutbinding1() {
		return BindingBuilder.bind(fanoutqueue1()).to(fanoutExchange());
	}
	@Bean(name="fanoutbinding2")
	public Binding fanoutbinding2() {
		return BindingBuilder.bind(fanoutqueue2()).to(fanoutExchange());
	}
	@Bean
	public MessageConverter msgcvt() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory conn) {
		RabbitTemplate rt=new RabbitTemplate(conn);
		rt.setMessageConverter(msgcvt());
		return rt;
	}
}

