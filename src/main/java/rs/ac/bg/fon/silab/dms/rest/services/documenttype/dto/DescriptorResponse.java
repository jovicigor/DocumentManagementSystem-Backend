/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.silab.dms.rest.services.documenttype.dto;

import rs.ac.bg.fon.silab.dms.core.model.Descriptor;

class DescriptorResponse {

    private Long id;
    private String name;

    DescriptorResponse(Descriptor descriptor) {
        this.id = descriptor.getId();
        this.name = descriptor.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
