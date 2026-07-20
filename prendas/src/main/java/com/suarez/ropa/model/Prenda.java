package com.suarez.ropa.model;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;


@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Prenda {

    private Integer id;
    private String prenda;
    private double precio;
    
}
