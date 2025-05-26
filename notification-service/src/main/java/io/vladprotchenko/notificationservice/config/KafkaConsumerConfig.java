package io.vladprotchenko.notificationservice.config;

//@Configuration
public class KafkaConsumerConfig {

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    public Map<String, Object> getKafkaConsumerConfig() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, RecipientListKafka> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(getKafkaConsumerConfig());
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, RecipientListKafka>> listenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, RecipientListKafka> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

}
