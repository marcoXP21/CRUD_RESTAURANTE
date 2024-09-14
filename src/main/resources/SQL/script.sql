USE [master]
GO
/****** Object:  Database [db_restaurant]    Script Date: 13/09/2024 23:22:26 ******/
CREATE DATABASE [db_restaurant]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'db_restaurant', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\db_restaurant.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'db_restaurant_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\db_restaurant_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [db_restaurant] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [db_restaurant].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [db_restaurant] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [db_restaurant] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [db_restaurant] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [db_restaurant] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [db_restaurant] SET ARITHABORT OFF 
GO
ALTER DATABASE [db_restaurant] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [db_restaurant] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [db_restaurant] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [db_restaurant] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [db_restaurant] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [db_restaurant] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [db_restaurant] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [db_restaurant] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [db_restaurant] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [db_restaurant] SET  DISABLE_BROKER 
GO
ALTER DATABASE [db_restaurant] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [db_restaurant] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [db_restaurant] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [db_restaurant] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [db_restaurant] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [db_restaurant] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [db_restaurant] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [db_restaurant] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [db_restaurant] SET  MULTI_USER 
GO
ALTER DATABASE [db_restaurant] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [db_restaurant] SET DB_CHAINING OFF 
GO
ALTER DATABASE [db_restaurant] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [db_restaurant] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [db_restaurant] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [db_restaurant] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [db_restaurant] SET QUERY_STORE = ON
GO
ALTER DATABASE [db_restaurant] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [db_restaurant]
GO
/****** Object:  Table [dbo].[Reservas]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reservas](
	[ReservaID] [int] NULL,
	[NombreCliente] [varchar](100) NOT NULL,
	[TelefonoCliente] [varchar](15) NULL,
	[FechaReserva] [varchar](15) NOT NULL,
	[HoraReserva] [varchar](15) NOT NULL,
	[NumeroPersonas] [int] NOT NULL,
	[Comentarios] [varchar](255) NULL,
	[Estado] [varchar](50) NULL,
	[Estado_bi] [bit] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Reservas] ADD  DEFAULT ('Pendiente') FOR [Estado]
GO
ALTER TABLE [dbo].[Reservas] ADD  DEFAULT ('0') FOR [Estado_bi]
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarReserva]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ActualizarReserva]
    @p_ReservaID INT,
    @p_NombreCliente NVARCHAR(100),
    @p_TelefonoCliente NVARCHAR(15),
    @p_FechaReserva NVARCHAR(15),
    @p_HoraReserva NVARCHAR(15),
    @p_NumeroPersonas INT,
    @p_Comentarios NVARCHAR(255),
    @p_Estado NVARCHAR(50)
AS
BEGIN
    UPDATE Reservas
    SET
        NombreCliente = @p_NombreCliente,
        TelefonoCliente = @p_TelefonoCliente,
        FechaReserva = @p_FechaReserva,
        HoraReserva = @p_HoraReserva,
        NumeroPersonas = @p_NumeroPersonas,
        Comentarios = @p_Comentarios,
        Estado = @p_Estado
    WHERE ReservaID = @p_ReservaID;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_BUSCAR_RESERVA_POR_ID]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 CREATE PROCEDURE [dbo].[SP_BUSCAR_RESERVA_POR_ID]
    @reservaid INT
AS
BEGIN
    SELECT * FROM Reservas WHERE ReservaID = @reservaid;
END;

GO
/****** Object:  StoredProcedure [dbo].[SP_EliminarReserva]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EliminarReserva]
    @p_ReservaID INT
AS
BEGIN
    UPDATE Reservas
    SET Estado_bi = 1  -- Marcamos la reserva como eliminada
    WHERE ReservaID = @p_ReservaID;
END
GO
/****** Object:  StoredProcedure [dbo].[SP_InsertarReserva]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_InsertarReserva]
    @ReservaID INT,
    @NombreCliente VARCHAR(100),
    @TelefonoCliente VARCHAR(15) = NULL,     -- Parámetro opcional
    @FechaReserva VARCHAR(15),
    @HoraReserva VARCHAR(15),
    @NumeroPersonas INT,
    @Comentarios VARCHAR(255) = NULL,        -- Parámetro opcional
    @Estado VARCHAR(50) = 'Pendiente'        -- Valor por defecto 'Pendiente'
AS
BEGIN
    -- Insertar los valores en la tabla Reservas
    INSERT INTO Reservas (ReservaID, NombreCliente, TelefonoCliente, FechaReserva, HoraReserva, NumeroPersonas, Comentarios, Estado)
    VALUES (@ReservaID, @NombreCliente, @TelefonoCliente, @FechaReserva, @HoraReserva, @NumeroPersonas, @Comentarios, @Estado);
    
    -- Mensaje de confirmación
    PRINT 'Reserva insertada exitosamente';
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ListarReservas]    Script Date: 13/09/2024 23:22:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ListarReservas]
AS
BEGIN
    SELECT ReservaID,
           NombreCliente,
           TelefonoCliente,
           FechaReserva,
           HoraReserva,
           NumeroPersonas,
           Comentarios,
           Estado
    FROM Reservas
	WHERE Estado_bi = 0;
END;
GO
USE [master]
GO
ALTER DATABASE [db_restaurant] SET  READ_WRITE 
GO
