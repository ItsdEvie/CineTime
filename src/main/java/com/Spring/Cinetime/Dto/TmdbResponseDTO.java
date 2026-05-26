package com.Spring.Cinetime.Dto;

import java.util.List;

public class TmdbResponseDTO {
    private List<TmdbResultDTO> results;

    public List<TmdbResultDTO> getResults() { return results; }
    public void setResults(List<TmdbResultDTO> results) { this.results = results; }
}
