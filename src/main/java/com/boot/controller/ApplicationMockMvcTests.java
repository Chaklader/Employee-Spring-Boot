//package com.boot.controller;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ApplicationMockMvcTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void checkPersonInfoWhenNameMissingNameThenFailure() throws Exception {
//        MockHttpServletRequestBuilder createPerson = post("/")
//                .param("age", "20");
//
//        mockMvc.perform(createPerson)
//            .andExpect(model().hasErrors());
//    }
//
//    @Test
//    public void checkPersonInfoWhenNameTooShortThenFailure() throws Exception {
//        MockHttpServletRequestBuilder createPerson = post("/")
//                .param("name", "R")
//                .param("age", "20");
//
//        mockMvc.perform(createPerson)
//            .andExpect(model().hasErrors());
//    }
//
//    @Test
//    public void checkPersonInfoWhenAgeMissingThenFailure() throws Exception {
//        MockHttpServletRequestBuilder createPerson = post("/")
//                .param("name", "Rob");
//
//        mockMvc.perform(createPerson)
//            .andExpect(model().hasErrors());
//    }
//
//    @Test
//    public void checkPersonInfoWhenAgeTooYoungThenFailure() throws Exception {
//        MockHttpServletRequestBuilder createPerson = post("/")
//                .param("age", "1")
//                .param("name", "Rob");
//
//        mockMvc.perform(createPerson)
//            .andExpect(model().hasErrors());
//    }
//
//    @Test
//    public void checkPersonInfoWhenValidRequestThenSuccess() throws Exception {
//        MockHttpServletRequestBuilder createPerson = post("/")
//                .param("name", "Rob")
//                .param("age", "20");
//
//        mockMvc.perform(createPerson)
//            .andExpect(model().hasNoErrors());
//    }
//}