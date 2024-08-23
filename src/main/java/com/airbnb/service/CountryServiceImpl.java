package com.airbnb.service;
import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto add(CountryDto countryDto) {
        Country entity = mapToEntity(countryDto);
        Country save = countryRepository.save(entity);
        CountryDto dto = mapToDto(save);
        return dto;
    }
    Country mapToEntity(CountryDto dto){
        Country entity = new Country();
        entity.setName(dto.getName());
        return entity;
    }

   CountryDto mapToDto(Country entity){
        CountryDto dto = new CountryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
