/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Roma
 * Created: 22.08.2016
 */

ALTER TABLE CONTACTS
   ADD CONSTRAINT PERSONS_FK Foreign Key (
      PERSONID)
   REFERENCES PERSONS (
      ID);

ALTER TABLE CONTACTS
   ADD CONSTRAINT ADDRESSES_FK Foreign Key (
      ADDRESSID)
   REFERENCES ADDRESSES (
      ID);

ALTER TABLE CONTACTS
   ADD CONSTRAINT HOMEPHONES_FK Foreign Key (
      HOMEPHONEID)
   REFERENCES PHONES (
      ID);

ALTER TABLE CONTACTS
   ADD CONSTRAINT WORKPHONES_FK Foreign Key (
      WORKPHONEID)
   REFERENCES PHONES (
      ID);