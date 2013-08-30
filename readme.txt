
    2013 (C) Alex Dobrianski Satellite encryption communication
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    Design and development by Team "Plan B" is licensed under 
    a Creative Commons Attribution-ShareAlike 3.0 Unported License.
    http://creativecommons.org/licenses/by-sa/3.0/ 

                                   Preamble.

Encryption of a data is essential part of a communication especially in space.

Current encryption methods uses different encryption algorithms such as RSA, 
AES, and etc. That algorithms based on a small key which allows encrypt all 
communication session, and assumption that communication will be secure as long 
as security measure will keep small key secret.

Current project utilize old (instead of modern) encryption technique: 
"Encryption notebook". Which with modern electronic data storage allow bypass 
limitation of a small size key.  As a source of random data for the big length, 
"Total" key, will be used processed, individually recorded, video stream (or any 
picture file). During communication used "key chunks" are not reusable.

Stage oh the development.

a)	Prototype for Windows based system. Prototype will include application 
        that will generate big encryption key from video data file, then will 
        use big key to encrypt any message and decrypt it. Encrypted message 
        can be send over regular communication channels. Control of the amount 
        of key's copies is in hands of a user.
b)	Android app can be useful for SMS encryption.
c)	PIC processor based communication for satellite communication.


                                    Challenge.
Translate this message:

=65000000a000000000000000ed45a19834912670eeaace8cc71a0da73a27aa9cff844d1fdc177c1794a5a3913f4757bcfb55a2a4fdda15ca0d0c3a811f3bb68233100d3f6f1f6bda9cdcf60cada1b2f776bf74a4ac66631efe07ff2be56771d058fd9d439517c773adbf7180a3ad0835f1=

using:
- source code provided, 
- key (used for encryption), 
- original video (used for key generation) published on youtube, 

Anybody provided correct decrypted message before 10 October, 2013 
will receive 10 Lunaro Sterlings award.

Avalable :
- source code:
- key file - 111.tkey
- youtube video -

Sample:
 - unencrypted text (same key file 111.tkey):
NOW IS THE TIME FOR ALL GOOD MEN TO COME TO THE AID OF THE PARTY

chiper text after encryption: 
=400000000000000000000000584fba6e203321df73fe0c07f8fa9903d4d2c80ff0dd62ad87413cf289f5e1251d44766b9156d76495a022263be1b04fa8ab075fe8cc44b0e45a4ed60a2cb727=
