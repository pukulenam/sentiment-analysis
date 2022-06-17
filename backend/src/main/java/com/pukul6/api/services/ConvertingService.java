package com.pukul6.api.services;

import com.pukul6.api.entities.Converting;
import com.pukul6.api.entities.dtos.ConvertingDto;
import com.pukul6.api.respositories.ConvertingRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConvertingService implements BaseService<Converting, ConvertingDto> {
	private final ConvertingRepository convertingRepository;
	private final CategoryService categoryService;
	
	@Override
	public Optional<Converting> save(Converting entity) {
		return Optional.of(Optional.of(convertingRepository.save(entity))).orElse(Optional.empty());
	}
	
	@Override
	public List<Converting> findAll() {
		return convertingRepository.findAll();
	}
	
	@Override
	public Optional<Converting> update(Converting entity) {
		return this.save(entity);
	}
	
	@Override
	public Optional<Converting> findById(String id) {
		return convertingRepository.findById(id);
	}
	
	@Override
	public Converting DtoToEntity(ConvertingDto dto) {
		return Converting.builder()
				.penulis(dto.getPenulis())
				.sampul(dto.getSampul())
				.sumberGb(dto.getSumberGb())
				.referensi(dto.getReferensi())
				.judul(dto.getJudul())
				.kategori(categoryService
						.findByName(dto.getKategory())
						.orElseThrow(() -> new NoSuchElementException(
								"Category with name " + dto.getKategory().toUpperCase() + " not found")))
				.news(dto.getNews())
				.build();
	}
	
	@Override
	public List<Converting> DtosToEntities(List<ConvertingDto> dtos) {
		return dtos.stream().map((dto) -> this.DtoToEntity(dto)).collect(Collectors.toList());
	}
	
}
