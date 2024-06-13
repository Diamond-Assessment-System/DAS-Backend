package com.project.DASBackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.DASBackend.dto.AssessmentRequestDto;
import com.project.DASBackend.service.AssessmentRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AssessmentRequestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssessmentRequestService assessmentRequestService;

    @InjectMocks
    private AssessmentRequestController assessmentRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assessmentRequestController).build();
    }

    @Test
    void createAssessmentRequest() throws Exception {
        AssessmentRequestDto requestDto = new AssessmentRequestDto();
        requestDto.setRequestId(1); // Set necessary fields for requestDto

        when(assessmentRequestService.createAssessmentRequest(any(AssessmentRequestDto.class))).thenReturn(requestDto);

        mockMvc.perform(post("/api/assessmentrequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isCreated());

        verify(assessmentRequestService, times(1)).createAssessmentRequest(any(AssessmentRequestDto.class));
    }

    @Test
    void getAssessmentRequestById() throws Exception {
        AssessmentRequestDto requestDto = new AssessmentRequestDto();
        requestDto.setRequestId(1); // Set necessary fields for requestDto

        when(assessmentRequestService.getAssessmentRequestById(1)).thenReturn(requestDto);

        mockMvc.perform(get("/api/assessmentrequests/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId").value(1));

        verify(assessmentRequestService, times(1)).getAssessmentRequestById(1);
    }

    @Test
    void getAllAssessmentRequests() throws Exception {
        AssessmentRequestDto requestDto1 = new AssessmentRequestDto();
        requestDto1.setRequestId(1); // Set necessary fields for requestDto1
        AssessmentRequestDto requestDto2 = new AssessmentRequestDto();
        requestDto2.setRequestId(2); // Set necessary fields for requestDto2
        List<AssessmentRequestDto> requestDtoList = Arrays.asList(requestDto1, requestDto2);

        when(assessmentRequestService.getAllAssessmentRequests()).thenReturn(requestDtoList);

        mockMvc.perform(get("/api/assessmentrequests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].requestId").value(1))
                .andExpect(jsonPath("$[1].requestId").value(2));

        verify(assessmentRequestService, times(1)).getAllAssessmentRequests();
    }

    @Test
    void updateAssessmentRequest() throws Exception {
        AssessmentRequestDto requestDto = new AssessmentRequestDto();
        requestDto.setRequestId(1); // Set necessary fields for requestDto

        when(assessmentRequestService.updateAssessmentRequest(any(AssessmentRequestDto.class), eq(1))).thenReturn(requestDto);

        mockMvc.perform(put("/api/assessmentrequests/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk());

        verify(assessmentRequestService, times(1)).updateAssessmentRequest(any(AssessmentRequestDto.class), eq(1));
    }

    @Test
    void deleteAssessmentRequest() throws Exception {
        mockMvc.perform(delete("/api/assessmentrequests/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Assessment request deleted successfully"));

        verify(assessmentRequestService, times(1)).deleteAssessmentRequest(1);
    }

    @Test
    void changeAssessmentRequestStatus() throws Exception {
        mockMvc.perform(put("/api/assessmentrequests/{id}/status", 1)
                        .param("status", "2"))
                .andExpect(status().isNoContent());

        verify(assessmentRequestService, times(1)).changeStatus(1, 2);
    }

    // Helper method to convert objects to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
