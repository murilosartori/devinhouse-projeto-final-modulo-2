package br.com.devinhouse.grupo5.core;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.model.Processo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ProcessoInputDTO.class, Processo.class)
                .<String>addMapping((src) ->
                        src.getSgOrgaoProcesso(),
                        (dest, value) -> dest.setChaveProcesso(value));

        return new ModelMapper();
    }

}
