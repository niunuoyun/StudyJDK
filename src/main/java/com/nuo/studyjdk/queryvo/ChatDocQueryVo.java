package com.nuo.studyjdk.queryvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class ChatDocQueryVo {
    String model = "chat-model-6B";
    private float frequency_penalty = 0;
    private float presence_penalty = 0;
    @JsonIgnore
    private Object stream;
    private int top_k = 20;
    private float top_p = 0.9f;
    private float   repetition_penalty = 0.9f;
    private float length_penalty;
    private float diversity_penalty;
    private int num_beams = 1;
    public int random_seed = 0;
    private String skillId="1234567890";

    @Override
    public String toString() {
        return "ChatDocQueryVo{" +
                "model='" + model + '\'' +
                ", frequency_penalty=" + frequency_penalty +
                ", presence_penalty=" + presence_penalty +
                ", stream=" + stream +
                ", top_k=" + top_k +
                ", top_p=" + top_p +
                ", repetition_penalty=" + repetition_penalty +
                ", length_penalty=" + length_penalty +
                ", diversity_penalty=" + diversity_penalty +
                ", num_beams=" + num_beams +
                ", random_seed=" + random_seed +
                ", skillId='" + skillId + '\'' +
                '}';
    }
}

