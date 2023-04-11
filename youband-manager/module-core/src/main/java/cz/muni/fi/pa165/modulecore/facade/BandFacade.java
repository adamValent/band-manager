package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.mapper.BandMapper;
import cz.muni.fi.pa165.modulecore.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandFacade {
    private final BandService bandService;
    private final BandMapper bandMapper;

    @Autowired
    public BandFacade(BandService bandService, BandMapper bandMapper) {
        this.bandService = bandService;
        this.bandMapper = bandMapper;
    }

    public BandDto findById(Long id) {
        return bandMapper.mapToDto(bandService.findById(id));
    }

    public List<BandDto> findAll() {
        return bandMapper.mapToList(bandService.findAll());
    }

    public BandDto createBand(BandDto bandDto) {
        return bandMapper.mapToDto(bandService.createBand(bandMapper.mapFromDto(bandDto)));
    }

    public BandDto updateBand(Long id, BandDto bandDto) {
        return bandMapper.mapToDto(bandService.updateBand(id, bandMapper.mapFromDto(bandDto)));
    }

    public void deleteBand(Long id) {
        bandService.deleteBand(id);
    }
}
