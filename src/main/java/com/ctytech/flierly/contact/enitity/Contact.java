package com.ctytech.flierly.contact.enitity;

import com.ctytech.flierly.address.entity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@SequenceGenerator(name = "contact_id_generator", sequenceName = "contact_id_seq", initialValue = 1000, allocationSize = 1)
@Getter
@Setter
@EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_generator")
    private Long id;

    @NotBlank(message = "{contact.name.absent}")
    private String name;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @NotBlank(message = "{contact.phone.absent}")
    @Column(length = 13)
    private String phone;

    @Digits(integer = 13, fraction = 0, message = "{phone.invalid}")
    @Column(length = 13)
    private String alternatePhone;

    @Email(message = "{email.invalid}")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "{email.invalid}")
    @NotBlank(message = "{contact.email.absent}")
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "contact_address_fkey"))
    private Address address;
}
