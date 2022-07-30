package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "producer")
public class Producer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producer_id")
	private Long id;
	
	@NotBlank(message = "*Vui lòng nhập nhà sản xuất!")
	@Column(name = "producer_code", length = 10, nullable = false)
	private String producerCode;
	
	@NotBlank(message = "*Vui lòng nhập tên nhà sản xuất!")
	@Column(name = "producer_name", length = 100, nullable = false)
	private String producerName;
	
	@NotBlank(message = "*Vui lòng nhập địa chỉ nhà sản xuất!")
	@Column(name = "producer_address", length = 100, nullable = false)
	private String producerAddress;
	
	@NotBlank(message = "*Vui lòng nhập email nhà sản xuất!")
	@Email(message = "Nhập đúng định dạng email!")
	@Column(name = "producer_email", length = 100, nullable = false)
	private String producerEmail;
	
	@NotBlank(message = "*Vui lòng nhập tên người đại diện!")
	@Column(name = "representative", length = 100, nullable = false)
	private String representative;

	@JsonIgnore
	@OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> book;
	
	
	
	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducerCode() {
		return producerCode;
	}

	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerAddress() {
		return producerAddress;
	}

	public void setProducerAddress(String producerAddress) {
		this.producerAddress = producerAddress;
	}

	public String getProducerEmail() {
		return producerEmail;
	}

	public void setProducerEmail(String producerEmail) {
		this.producerEmail = producerEmail;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

}
