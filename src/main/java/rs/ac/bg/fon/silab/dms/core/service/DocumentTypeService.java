package rs.ac.bg.fon.silab.dms.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.dms.core.exception.DMSErrorException;
import rs.ac.bg.fon.silab.dms.core.model.Descriptor;
import rs.ac.bg.fon.silab.dms.core.model.DocumentType;
import rs.ac.bg.fon.silab.dms.core.repository.DescriptorRepository;
import rs.ac.bg.fon.silab.dms.core.repository.DocumentTypeRepository;

@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentRepository;

    @Autowired
    private DescriptorRepository descriptorRepository;

    DocumentTypeService(DocumentTypeRepository documentRepository, DescriptorRepository descriptorRepository) {
        this.documentRepository = documentRepository;
        this.descriptorRepository = descriptorRepository;
    }

    public DocumentType createDocumentType(DocumentType documentType) throws DMSErrorException {
        validateDocumentType(documentType);
        for (Descriptor descriptor : documentType.getDescriptors()) {
            descriptorRepository.save(descriptor);
        }
        descriptorRepository.flush();
        return documentRepository.saveAndFlush(documentType);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return documentRepository.findAll();
    }

    public DocumentType getDocumentType(Long id) throws DMSErrorException {
        DocumentType documentType = documentRepository.findOne(id);
        if (documentType == null) {
            throw new DMSErrorException("Document type does not exists.");
        }
        return documentType;
    }

    void validateDocumentType(DocumentType documentType) throws DMSErrorException {
        if (documentRepository.getByNameAndCompanyId(documentType.getName(), documentType.getCompany().getId()) != null) {
            throw new DMSErrorException("Document type with given name already exists.");
        }
    }

}
