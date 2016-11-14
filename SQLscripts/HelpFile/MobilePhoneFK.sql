/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Roma
 * Created: 22.08.2016
 */

ALTER TABLE MOBILEPHONES
   ADD CONSTRAINT CONTACTS_FK Foreign Key (
      CONTACTID)
   REFERENCES CONTACTS (
      ID);