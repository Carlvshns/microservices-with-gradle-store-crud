package dev.carlvs.product.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.validation.BindingResult;

import lombok.Builder;

@Builder
public class ErrorMessage {
    
    private String code;
    private List<Map<String, String>> messages;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<Map<String, String>> getMessages() {
        return messages;
    }
    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    } 

    public static String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors()
                                                .stream()
                                                .map(res -> {
                                                    Map<String, String> error = new HashMap<>();
                                                    error.put(res.getField(), res.getDefaultMessage());
                                                    return error;
                                                    }).collect(Collectors.toList());
        
        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
