package com.br.psi.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.psi.model.Const;
import com.br.psi.model.FilePatient;
import com.br.psi.model.FileProfessional;
import com.br.psi.model.Patient;
import com.br.psi.model.Professional;
import com.br.psi.model.User;
import com.br.psi.repository.FilePatientRepository;
import com.br.psi.repository.FileProfessionalRepository;
import com.br.psi.repository.PatientRepository;
import com.br.psi.repository.ProfessionalRepository;

@RestController
@RequestMapping
public class FileController {
	private static final String path = "/home/fabiofreitas1989/uploads/";
    @Autowired
    private ProfessionalRepository professionalRepository;
    
    @Autowired
    private PatientRepository ratientRepository;
    
    @Autowired
    private FileProfessionalRepository fileProfessionalRepository;
    
    @Autowired
    private FilePatientRepository filePatientRepository;
    
    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    private HttpServletRequest request;
    
    @SuppressWarnings("resource")
	@Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/saveProfessionalFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileProfessional> saveProfessionalFile(
    		@RequestParam(value = "file", required = true) MultipartFile file,
    		@RequestParam(value = "description", required = true) String description,
    		@RequestParam(value = "professional", required = true) Long professional
    		) throws  IOException{
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 String realPathtoUploads =  path;
    	 request.getServletContext().getRealPath(path);
    	 if (!new File(realPathtoUploads).exists()) {
    		 new File(realPathtoUploads).mkdir();
    	 }
    	 
    	 if (!new File(realPathtoUploads + user.getPerson().getClient().getId().toString()).exists()) {
				new File(realPathtoUploads + user.getPerson().getClient().getId().toString()).mkdir();
		 }
    	 
    	 if (!new File(realPathtoUploads + user.getPerson().getClient().getId().toString()+"/professional/").exists()) {
				new File(realPathtoUploads + user.getPerson().getClient().getId().toString()+"/professional/").mkdir();
		 }
    	 
    	 realPathtoUploads += user.getPerson().getClient().getId().toString()+"/professional/"+professional+"/";
			
    	 if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
		 }
    	FileProfessional fileProfessional = new FileProfessional();
    	
    	
    	LocalDate now = LocalDate.now();
    	String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + now+ orgName ;
        File dest = new File(filePath);
        file.transferTo(dest);
        
    	fileProfessional.setProfessional(professionalRepository.findAllById(professional));
    	fileProfessional.setDescription(description);
    	fileProfessional.setPath(dest.getPath());
    	fileProfessional.setSize(file.getSize());
    	fileProfessional.setContentType(file.getContentType());
    	fileProfessionalRepository.save(fileProfessional);
    	
        return new ResponseEntity<FileProfessional>(fileProfessional, HttpStatus.CREATED);
    }
    
    @SuppressWarnings("resource")
	@Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/file/savePatientFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilePatient> savePatientFile(
    		@RequestParam(value = "file", required = true) MultipartFile file,
    		@RequestParam(value = "description", required = true) String description,
    		@RequestParam(value = "patient", required = true) Long id
    		) throws  IOException{
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String realPathtoUploads =  path;
         
    	 if (!new File(realPathtoUploads).exists()) {
    		 new File(realPathtoUploads).mkdir();
    	 }
    	 
    	 if (!new File(realPathtoUploads + user.getPerson().getClient().getId().toString()).exists()) {
				new File(realPathtoUploads + user.getPerson().getClient().getId().toString()).mkdir();
		 }
    	 
    	 if (!new File(realPathtoUploads + user.getPerson().getClient().getId().toString()+"/paciente/").exists()) {
				new File(realPathtoUploads + user.getPerson().getClient().getId().toString()+"/paciente/").mkdir();
		 }
    	 
    	 realPathtoUploads += user.getPerson().getClient().getId().toString()+"/paciente/"+id+"/";
         
    	 if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
		 }
    	 
    	FilePatient filePatient = new FilePatient();
    	
    	
    	LocalDate now = LocalDate.now();
    	String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + now + orgName;
        File dest = new File(filePath);
        file.transferTo(dest);
        filePatient.setPatient(ratientRepository.findAllById(id));
        filePatient.setDescription(description);
    	filePatient.setPath(dest.getPath());
    	filePatient.setSize(file.getSize());
    	filePatient.setContentType(file.getContentType());
    	filePatientRepository.save(filePatient);
    	
        return new ResponseEntity<FilePatient>(filePatient, HttpStatus.CREATED);
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/file/findByPatient", method = RequestMethod.POST)
    public ResponseEntity<List<FilePatient>> findByPatient(@RequestBody Patient patient){
    	List<FilePatient> findAllByPatient = filePatientRepository.findAllByPatient(patient);
    		return new ResponseEntity<List<FilePatient>>(findAllByPatient, HttpStatus.OK);
	}

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/findByProfessional", method = RequestMethod.POST)
    public ResponseEntity<List<FileProfessional>> findByProfessional(@RequestBody Professional professional){
    	List<FileProfessional> findAllByProfessional = fileProfessionalRepository.findAllByProfessional(professional);
    		return new ResponseEntity<List<FileProfessional>>(findAllByProfessional, HttpStatus.OK);
	}
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/removeFileProfessional", method = RequestMethod.POST)
    public ResponseEntity removeFileProfessional(@RequestBody FileProfessional fileProfessional){
	    	if(new File(fileProfessional.getPath()).delete()) {
	    		fileProfessionalRepository.delete(fileProfessional);
	    	}
    return new ResponseEntity(HttpStatus.OK);
	}
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/removeFilePatient", method = RequestMethod.POST)
    public ResponseEntity removeFilePatient(@RequestBody FilePatient filePatient){
	    	if(new File(filePatient.getPath()).delete()) {
	    		filePatientRepository.delete(filePatient);
	    	}
    return new ResponseEntity(HttpStatus.OK);
	}
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/download", method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource>  download(HttpServletResponse response, @RequestBody FileProfessional fileProfessional) throws IOException{
    	File file = new File(fileProfessional.getPath());
    	if (file.exists()) {

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			return (ResponseEntity<InputStreamResource>) ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()).contentType(getMediaTypeForFileName(this.servletContext, mimeType)).contentLength(file.length()).body(resource);
		}

    	return null;
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/file/downloadPatient", method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource>  downloadPatient(HttpServletResponse response, @RequestBody FilePatient filePatient) throws IOException{
    	File file = new File(filePatient.getPath());
    	if (file.exists()) {

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			return (ResponseEntity<InputStreamResource>) ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()).contentType(getMediaTypeForFileName(this.servletContext, mimeType)).contentLength(file.length()).body(resource);
		}

    	return null;
    }
    
    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String mineType) {
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    
}
