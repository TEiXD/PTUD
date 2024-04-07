CREATE DATABASE BanVeTau;
use BanVeTau

CREATE TABLE NhaGa (
    maNhaGa NVARCHAR(3) PRIMARY KEY,
    tenNhaGa NVARCHAR(100) NOT NULL
);
GO

CREATE TABLE KhachHang (
    maKH NVARCHAR(8) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    gioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    email NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE NhanVien (
    maNV NVARCHAR(8) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    gioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    ngaySinh DATE NOT NULL,
    trinhDo NVARCHAR(100) NOT NULL,
    maNhaGa NVARCHAR(3),
    FOREIGN KEY (maNhaGa) REFERENCES NhaGa(maNhaGa)
);
GO

CREATE TABLE Tau (
    maTau NVARCHAR(10) PRIMARY KEY,
    loaiTau NVARCHAR(50) NOT NULL,
    maNhaGa NVARCHAR(3),
    FOREIGN KEY (maNhaGa) REFERENCES NhaGa(maNhaGa)
);
GO

CREATE TABLE ChuyenTau (
    maChuyenTau NVARCHAR(10) PRIMARY KEY,
    gaDi NVARCHAR(100) NOT NULL,
    gaDen NVARCHAR(100) NOT NULL,
    gioDi DATETIME NOT NULL,
    gioDen DATETIME NOT NULL,
    loaiTau NVARCHAR(10),
    FOREIGN KEY (loaiTau) REFERENCES Tau(maTau)
);
GO

CREATE TABLE Ve (
    maVe NVARCHAR(10) PRIMARY KEY,
    tenVe NVARCHAR(100) NOT NULL,
    loaiVe NVARCHAR(10) NOT NULL,
    ngayDi DATETIME NOT NULL,
    ngayVe DATETIME NOT NULL,
    maKH NVARCHAR(8),
    maNV NVARCHAR(8),
    maChuyenTau NVARCHAR(10),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maChuyenTau) REFERENCES ChuyenTau(maChuyenTau)
);
GO

CREATE TABLE Toa (
    maTau NVARCHAR(10),
    soToaTau NVARCHAR(10) PRIMARY KEY,
    loaiTau NVARCHAR(100),
    soPhong INT,
    maGhe NVARCHAR(10),
	SoGhe int,
    FOREIGN KEY (maTau) REFERENCES Tau(maTau)
);
GO

--Dữ liệu mẫu cho bảng NhaGa
INSERT INTO NhaGa (maNhaGa, tenNhaGa) VALUES 
    (N'NGA', N'Nhà ga A'),
    (N'NGB', N'Nhà ga B'),
    (N'NGC', N'Nhà ga C'),
    (N'NGD', N'Nhà ga D'),
    (N'NGE', N'Nhà ga E'),
    (N'NGF', N'Nhà ga F'),
    (N'NGG', N'Nhà ga G'),
    (N'NGH', N'Nhà ga H'),
    (N'NGI', N'Nhà ga I'),
    (N'NGK', N'Nhà ga K');

SELECT * FROM [dbo].[NhaGa];

--Dữ liệu mẫu cho bảng KhachHang
INSERT INTO KhachHang (maKH, hoTen, CCCD, gioiTinh, SDT, email) VALUES 
	('KH001', N'Nguyễn Văn A', '012345678901', N'Nam', '0123456789', N'nva@gmail.com'),
	('KH002', N'Lê Thị B', '012345678902', N'Nữ', '0123456790', N'ltb@gmail.com'),
	('KH003', N'Trần Văn C', '012345678903', N'Nam', '0123456798', N'tvc@gmail.com'),
	('KH004', N'Phạm Thị D', '012345678904', N'Nữ', '0123456791', N'ptd@gmail.com'),
	('KH005', N'Hoàng Văn E', '012345678905', N'Nam', '0123456797', N'hve@gmail.com'),
	('KH006', N'Vũ Thị F', '012345678906', N'Nữ', '0123456792', N'vtf@gmail.com'),
	('KH007', N'Đặng Văn G', '012345678907', N'Nam', '0123456793', N'dvg@gmail.com'),
	('KH008', N'Trương Thị H', '012345678908', N'Nữ', '0123456794', N'tth@gmail.com'),
	('KH009', N'Lý Văn I', '012345678909', N'Nam', '0123456795', N'lvi@gmail.com'),
	('KH010', N'Nguyễn Thị K', '012345678910', N'Nữ', '0123456796', N'ntk@gmail.com');


SELECT * FROM [dbo].[KhachHang];

--Dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (maNV, hoTen, CCCD, gioiTinh, SDT, email, namSinh, trinhDo, maNhaGa) VALUES 
    ('NV001', N'Nguyễn Văn A', '012345678901', N'Nam', '0123456789', 'nva@gmail.com', '1990-01-01', N'Cử nhân', N'NGA'),
    ('NV002', N'Lê Thị B', '012345678902', N'Nữ', '0123456790', 'ltb@gmail.com', '1995-02-02', N'Cao đẳng', N'NGA'),
    ('NV003', N'Trần Văn C', '012345678903', N'Nam', '0123456798', 'tvc@gmail.com', '1992-03-03', N'Đại học', N'NGA'),
    ('NV004', N'Phạm Thị D', '012345678904', N'Nữ', '0123456791', 'ptd@gmail.com', '1997-04-04', N'Trung cấp', N'NGA'),
    ('NV005', N'Hoàng Văn E', '012345678905', N'Nam', '0123456797', 'hve@gmail.com', '1994-05-05', N'Đại học', N'NGA'),
    ('NV006', N'Nguyễn Thị F', '098345678906', N'Nữ', '0901234567', 'ntf@gmail.com', '1991-06-06', N'Cao đẳng', N'NGA'),
    ('NV007', N'Lê Văn G', '098345678907', N'Nam', '0123456890', 'lvg@gmail.com', '1998-07-07', N'Trung cấp', N'NGA'),
    ('NV008', N'Trần Thị H', '098345678908', N'Nữ', '0235678901', 'tth@gmail.com', '1993-08-08', N'Cử nhân', N'NGA'),
    ('NV009', N'Phạm Văn I', '098345678909', N'Nam', '0347890123', 'pvi@gmail.com', '1996-09-09', N'Đại học', N'NGA'),
    ('NV010', N'Hoàng Thị K', '098345678910', N'Nữ', '0889012345', 'htk@gmail.com', '1999-10-10', N'Cao đẳng', N'NGA');

SELECT * FROM [dbo].[NhanVien];

--Dữ liệu mẫu cho bảng Tau
INSERT INTO Tau (maTau, loaiTau, maNhaGa) VALUES 
	(N'TT001', N'Tàu hỏa', N'NGA'),
    (N'TT002', N'Tàu hỏa', N'NGB'),
    (N'TT003', N'Tàu cao tốc', N'NGC'),
    (N'TT004', N'Tàu cao tốc', N'NGD'),
    (N'TT005', N'Tàu hỏa', N'NGE'),
    (N'TT006', N'Tàu cao tốc', N'NGF'),
    (N'TT007', N'Tàu hỏa', N'NGG'),
    (N'TT008', N'Tàu cao tốc', N'NGH'),
    (N'TT009', N'Tàu hỏa', N'NGI'),
    (N'TT010', N'Tàu cao tốc', N'NGK');
SELECT * FROM Tau;

----Dữ liệu mẫu cho bảng ChuyenTau
INSERT INTO ChuyenTau (maChuyenTau, gaDi, gaDen, gioDi, gioDen, loaiTau) VALUES 
    (N'CT001', N'Ga A', N'Ga B', '2024-04-01 08:00:00', '2024-04-01 10:00:00', N'Tàu hỏa'),
    (N'CT002', N'Ga B', N'Ga C', '2024-04-01 10:30:00', '2024-04-01 12:30:00', N'Tàu hỏa'),
    (N'CT003', N'Ga C', N'Ga D', '2024-04-01 13:00:00', '2024-04-01 15:00:00', N'Tàu hỏa'),
    (N'CT004', N'Ga D', N'Ga E', '2024-04-01 15:30:00', '2024-04-01 17:30:00', N'Tàu hỏa'),
    (N'CT005', N'Ga E', N'Ga F', '2024-04-01 18:00:00', '2024-04-01 20:00:00', N'Tàu hỏa'),
    (N'CT006', N'Ga F', N'Ga G', '2024-04-01 20:30:00', '2024-04-01 22:30:00', N'Tàu cao tốc'),
    (N'CT007', N'Ga G', N'Ga H', '2024-04-01 23:00:00', '2024-04-02 01:00:00', N'Tàu cao tốc'),
    (N'CT008', N'Ga H', N'Ga I', '2024-04-02 01:30:00', '2024-04-02 03:30:00', N'Tàu cao tốc'),
    (N'CT009', N'Ga I', N'Ga K', '2024-04-02 04:00:00', '2024-04-02 06:00:00', N'Tàu cao tốc'),
    (N'CT010', N'Ga K', N'Ga A', '2024-04-02 06:30:00', '2024-04-02 08:30:00', N'Tàu cao tốc');


SELECT * FROM [dbo].[ChuyenTau];

--Dữ liệu mẫu cho bảng Ve
INSERT INTO Ve (maVe, tenVe, loaiVe, ngayDi, ngayVe, maKH, maNV, maChuyenTau) VALUES 
    (N'V001', N'Vé 1', N'Loại 1', '2024-04-01 08:00:00', '2024-04-01 10:00:00', N'KH001', N'NV001', N'CT001'),
    (N'V002', N'Vé 2', N'Loại 2', '2024-04-01 10:30:00', '2024-04-01 12:30:00', N'KH002', N'NV002', N'CT002'),
    (N'V003', N'Vé 3', N'Loại 1', '2024-04-01 13:00:00', '2024-04-01 15:00:00', N'KH003', N'NV003', N'CT003'),
    (N'V004', N'Vé 4', N'Loại 2', '2024-04-01 15:30:00', '2024-04-01 17:30:00', N'KH004', N'NV004', N'CT004'),
    (N'V005', N'Vé 5', N'Loại 1', '2024-04-01 18:00:00', '2024-04-01 20:00:00', N'KH005', N'NV005', N'CT005'),
    (N'V006', N'Vé 6', N'Loại 2', '2024-04-01 20:30:00', '2024-04-01 22:30:00', N'KH006', N'NV006', N'CT006'),
    (N'V007', N'Vé 7', N'Loại 1', '2024-04-01 23:00:00', '2024-04-02 01:00:00', N'KH007', N'NV007', N'CT007'),
    (N'V008', N'Vé 8', N'Loại 2', '2024-04-02 01:30:00', '2024-04-02 03:30:00', N'KH008', N'NV008', N'CT008'),
    (N'V009', N'Vé 9', N'Loại 1', '2024-04-02 04:00:00', '2024-04-02 06:00:00', N'KH009', N'NV009', N'CT009'),
    (N'V010', N'Vé 10', N'Loại 2', '2024-04-02 06:30:00', '2024-04-02 08:30:00', N'KH010', N'NV010', N'CT010');

SELECT * FROM [dbo].[Ve];

--Dữ liệu mẫu cho bảng Toa
INSERT INTO Toa (maTau, soToaTau, loaiTau, soPhong, maGhe) VALUES 
    (N'TT001', N'Toa001', N'Tàu hỏa', 10, N'G001'),
    (N'TT001', N'Toa002', N'Tàu hỏa', 12, N'G002'),
    (N'TT002', N'Toa003', N'Tàu hỏa', 8, N'G003'),
    (N'TT002', N'Toa004', N'Tàu hỏa', 10, N'G004'),
    (N'TT003', N'Toa005', N'Tàu cao tốc', 6, N'G005'),
    (N'TT003', N'Toa006', N'Tàu cao tốc', 8, N'G006'),
    (N'TT004', N'Toa007', N'Tàu cao tốc', 10, N'G007'),
    (N'TT004', N'Toa008', N'Tàu cao tốc', 12, N'G008'),
    (N'TT005', N'Toa009', N'Tàu hỏa', 8, N'G009'),
    (N'TT005', N'Toa010', N'Tàu hỏa', 10, N'G010');

SELECT * FROM [dbo].[Toa];